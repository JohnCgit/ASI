var port = 8082;
//Supprime tous les enfants du noeud d'id arg
function funClear(arg) {
    myNode = document.getElementById(arg);
    while (myNode.firstChild) {
        myNode.removeChild(myNode.lastChild);
    }
}

//Génère le contenu par défaut de la page index.html 
// Par défaut, cette page permet d'accéder aux espaces d'achat, de vente et de jeu
function funDefault() {
    funClear("content_header");
    funClear("content_body");
    var achat = document.createElement("button");
    achat.appendChild(document.createTextNode('achat'));

    var vente = document.createElement("button");
    vente.appendChild(document.createTextNode('vente'));

    var jeu = document.createElement("button");
    jeu.appendChild(document.createTextNode('jeu'));

    document.getElementById('content_body').appendChild(achat);
    document.getElementById('content_body').appendChild(vente);
    document.getElementById('content_body').appendChild(jeu);
    achat.onclick = funAchat;
    vente.onclick = funVente;
    jeu.onclick = funJeu;

}

// Génère un bouton permettant de retourner sur la page par défaut
function funReturnDefault() {
    var def = document.createElement("button");
    def.appendChild(document.createTextNode('Retour arrière'));
    document.getElementById('content_body').appendChild(def);
    def.onclick = funDefault;

}

// Génère le contenu de l'espace achat:
// Un tableau contenant les informations des cartes sur le marché
function funAchat() {
    funClear("content_body");
    document.getElementById("content_header").textContent = ('Achat');
    createFillTabCard(JSON.parse(loadRessource(`http://127.0.0.1:${port}/market/getAll`, "GET")), true);
    funReturnDefault();
}

// Génère le contenu de l'espace vente:
// Un tableau contenant les informations des cartes dans la collection du joueur

function funVente() {
    funClear("content_body");
    var Name = new URLSearchParams(window.location.search).get("Name");
    document.getElementById("content_header").textContent = ('Vente');
    var idUser = JSON.parse(loadRessource(`http://127.0.0.1:${port}/user/name/${Name}`, "GET")).id;
    var listI = JSON.parse(loadRessource(`http://127.0.0.1:${port}/user/getCollection/${idUser}`, "GET"));
    var listCard = []
    listI.forEach(idCard =>
        listCard.push(JSON.parse(loadRessource(`http://127.0.0.1:${port}/card/${idCard}`, "GET"))));
    createFillTabCard(listCard, false);
    funReturnDefault();

}

// Génère le contenu de l'espace jeu
function funJeu() {
    funClear("content_body");
    document.getElementById("content_header").textContent = ('Jeu');
    var ListRoom = JSON.parse(loadRessource(`http://127.0.0.1:${port}/lobby/getAll`, "GET"));
    createFillTabRoom(ListRoom);
    var create = document.createElement("button");
    create.appendChild(document.createTextNode("Create a room"));
    document.getElementById('content_body').appendChild(create);
    create.onclick = function() { select() };
    funReturnDefault();

}

function room(RoomId) {
    funClear("content_body");
    document.getElementById("content_header").textContent = (`Battle room ${RoomId}`);
    var Room = JSON.parse(loadRessource(`http://127.0.0.1:${port}/lobby/${RoomId}`, "GET"));
    if (Room.idPlayer2 == null) {
        document.getElementById("content_body").innerHTML = "Please wait";
        var start = new Date().getTime();
        milliseconds = 1000;
        for (var i = 0; i < 1e7; i++) {
            if ((new Date().getTime() - start) > milliseconds) {
                break;
            }
        }
        room(RoomId)
    } else {
        document.getElementById("content_body").innerHTML = JSON.parse(loadRessource(`http://127.0.0.1:${port}/lobby/Play/${RoomId}`, "GET"));
    }

}

function create() {
    var mise = parseInt(prompt("Votre mise:"));
    loadRessource(`http://127.0.0.1:${port}/lobby/create/${idUser}/${CardId}/${mise}`, "POST");
    room(RoomId);

}
/* function loadRessource(source, method) {
    var xhttp = new XMLHttpRequest();
    xhttp.open(method, source, true);
    xhttp.overrideMimeType("apPlication/json");
    xhttp.send();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            valueCallBack(this.response);
        }
    };
} */

// Fonction permettant la récupération d'une information située à l'url source avec la méthode method
function loadRessource(source, method) {
    var xhttp = new XMLHttpRequest();
    xhttp.open(method, source, false);
    xhttp.overrideMimeType("apPlication/json");
    xhttp.send();
    if (xhttp.status == 200) {
        return xhttp.response;
    }
};

// Permet l'affichage des liens vers les pages de connexion et d'inscription dans la zone content_header
function funUserDefault() {
    funClear("user_info")

    var login = document.createElement("a");
    login.setAttribute("href", "login.html");
    login.textContent = "Login";
    document.getElementById("user_info").appendChild(login);

    var sub = document.createElement("a");
    sub.setAttribute("href", "sub.html");
    sub.textContent = "Subscribe";
    document.getElementById("user_info").appendChild(sub);
}

// Lorsque l'utilisateur est connecté fait apparaître son nom et sa cagnotte dans la zone content_header
function funUser(Name) {
    funClear("user_info")
    var user = JSON.parse(loadRessource(`http://127.0.0.1:${port}/user/name/${Name}`, "GET"));
    var money = document.createElement("p");
    money.textContent = user.money;
    document.getElementById("user_info").appendChild(money);

    var profil = document.createElement("p");
    profil.textContent = user.name;
    document.getElementById("user_info").appendChild(profil);
}

// Permet de gérer l'achat d'une carte côté client et côté serveur 
function achat(TransactionId) {
    var Name = new URLSearchParams(window.location.search).get("Name");
    var idUser = JSON.parse(loadRessource(`http://127.0.0.1:${port}/user/name/${Name}`, "GET")).id;
    loadRessource(`http://127.0.0.1:${port}/market/buy/${TransactionId}/${idUser}`, "POST");
    funClear(TransactionId);
    document.getElementById(TransactionId).parentElement.remove();
    funUser(Name);
}

// Permet de gérer la vente d'une carte côté client et côté serveur 
function vente(CardId) {
    var Name = new URLSearchParams(window.location.search).get("Name");
    var idUser = JSON.parse(loadRessource(`http://127.0.0.1:${port}/user/name/${Name}`, "GET")).id;
    loadRessource(`http://127.0.0.1:${port}/market/sell/${CardId}/${idUser}`, "POST");
    funClear(CardId);
    document.getElementById(CardId).parentElement.remove();
}


function join(CardId, RoomId) {
    funClear("content_body");
    document.getElementById("content_header").textContent = ('Battle Room');
    var Name = new URLSearchParams(window.location.search).get("Name");
    var idUser = JSON.parse(loadRessource(`http://127.0.0.1:${port}/user/name/${Name}`, "GET")).id;
    loadRessource(`http://127.0.0.1:${port}/lobby/join/${CardId}/${idUser}`, "POST");
    room(RoomId);

}

function select(RoomId) {
    funClear("content_body");
    document.getElementById("content_header").textContent = ('Selection');
    var Name = new URLSearchParams(window.location.search).get("Name");
    var idUser = JSON.parse(loadRessource(`http://127.0.0.1:${port}/user/name/${Name}`, "GET")).id;
    var listI = JSON.parse(loadRessource(`http://127.0.0.1:${port}/user/getCollection/${idUser}`, "GET"));
    var listCard = []
    listI.forEach(idCard =>
        listCard.push(JSON.parse(loadRessource(`http://127.0.0.1:${port}/card/${idCard}`, "GET"))));
    document.getElementById("content_body").innerHTML +=
        `<table style="width:70%" id="tab">
    <tr>
        <th>Name</th>
        <th>Family</th>
        <th>Affinity</th>
        <th>Energy</th>
        <th>HP</th>
    </tr>
</table>`;
    listCard.forEach(Card => {
        if (RoomId == null) {
            document.getElementById("tab").innerHTML +=
                `<tr id="${Card.id}">
        <th>${Card.name}</th>
        <th>${Card.family}</th>
        <th>${Card.affinity}</th>
        <th>${Card.energy}</th>
        <th>${Card.hp}</th>
        <th><button onclick= "create(${Card.id})"> Fight </th>
    </tr>`;
        } else {
            document.getElementById("tab").innerHTML +=
                `<tr id="${Card.id}">
        <th>${Card.name}</th>
        <th>${Card.family}</th>
        <th>${Card.affinity}</th>
        <th>${Card.energy}</th>
        <th>${Card.hp}</th>
        <th><button onclick= "join(${Card.id,RoomId})"> Fight </th>
    </tr>`;
        }
    });
    funReturnDefault();
}

// Permet de créer et de remPlir un tableau contenant les informations sur une liste de cartes
// Si bool vaut true: affiche le tableau des achats
// Sinon affiche celui des ventes
function createFillTabCard(ListCard, bool) {
    document.getElementById("content_body").innerHTML +=
        `<table style="width:70%" id="tab">
        <tr>
            <th>Name</th>
            <th>Family</th>
            <th>Affinity</th>
            <th>Energy</th>
            <th>HP</th>
            <th>Price</th>
        </tr>
    </table>`;
    if (bool) {
        ListCard.forEach(Transaction => {
            var Card = JSON.parse(loadRessource(`http://127.0.0.1:${port}/card/${Transaction.idCard}`, "GET"));
            document.getElementById("tab").innerHTML +=
                `<tr id="${Transaction.id}">
            <th>${Card.name}</th>
            <th>${Card.family}</th>
            <th>${Card.affinity}</th>
            <th>${Card.energy}</th>
            <th>${Card.hp}</th>
            <th>${Card.price}</th>
            <th><button onclick= "achat(${Transaction.id})"> achat </th>
        </tr>`;
        });

    } else {
        ListCard.forEach(Card => {
            document.getElementById("tab").innerHTML +=
                `<tr id="${Card.id}">
            <th>${Card.name}</th>
            <th>${Card.family}</th>
            <th>${Card.affinity}</th>
            <th>${Card.energy}</th>
            <th>${Card.hp}</th>
            <th>${Card.price}</th>
            <th><button onclick= "vente(${Card.id})"> vente </th>
        </tr>`;
        });
    }


}

// Permet de créer et de remPlir un tableau contenant les informations sur une liste de cartes
// Si bool vaut true: affiche le tableau des achats
// Sinon affiche celui des ventes
function createFillTabRoom(ListRoom) {
    document.getElementById("content_body").innerHTML +=
        `<table style="width:70%" id="tab">
        <tr>
            <th>Challenger</th>
            <th>Bet</th>

        </tr>
    </table>`;
    ListRoom.forEach(Room => {
        var Player1 = JSON.parse(loadRessource(`http://127.0.0.1:${port}/user/id/${Room.idPlayer1}`, "GET"));
        document.getElementById("tab").innerHTML +=
            `<tr id="${Room.id}">
        <th>${Player1}</th>
        <th>${Room.mise}</th>
        <th><button onclick= "select(${Room.id})"> Select a Card to fight </th>
    </tr>`;
    });



}

// Lors du chargement de la page:
//si l'utilisateur est connecté alors le contenu_header fait apparaître son nom et sa cagnotte
//sinon le contenu_header contient des liens vers les pages de connexion et d'inscription 
window.onload = function() {
    funDefault();
    var Name = new URLSearchParams(window.location.search).get("Name");
    var Password = new URLSearchParams(window.location.search).get("Password");
    if (Name != null) {
        if (loadRessource(`http://127.0.0.1:${port}/login/${Name}/${Password}`, "GET") == "true") {
            funUser(Name);
        }
    } else {
        funUserDefault();
    }
};
var port = 8082;
//Supprime tous les enfants du noeud d'id arg
function funClear(arg) {
    myNode = document.getElementById(arg);
    while (myNode.firstChild) {
        myNode.removeChild(myNode.lastChild);
    }
}

//Génère le contenu par défaut de la page index.html 
// Par défaut, cette page permet d'accéder aux espaces d'achat, de vente et de jeu
function funDefault() {
    funClear("content_header");
    funClear("content_body");
    var achat = document.createElement("button");
    achat.appendChild(document.createTextNode('achat'));

    var vente = document.createElement("button");
    vente.appendChild(document.createTextNode('vente'));

    var jeu = document.createElement("button");
    jeu.appendChild(document.createTextNode('jeu'));

    document.getElementById('content_body').appendChild(achat);
    document.getElementById('content_body').appendChild(vente);
    document.getElementById('content_body').appendChild(jeu);
    achat.onclick = funAchat;
    vente.onclick = funVente;
    jeu.onclick = funJeu;

}

// Génère un bouton permettant de retourner sur la page par défaut
function funReturnDefault() {
    var def = document.createElement("button");
    def.appendChild(document.createTextNode('Retour arrière'));
    document.getElementById('content_body').appendChild(def);
    def.onclick = funDefault;

}

// Génère le contenu de l'espace achat:
// Un tableau contenant les informations des cartes sur le marché
function funAchat() {
    funClear("content_body");
    document.getElementById("content_header").textContent = ('Achat');
    createFillTabCard(JSON.parse(loadRessource(`http://127.0.0.1:${port}/market/getAll`, "GET")), true);
    funReturnDefault();
}

// Génère le contenu de l'espace vente:
// Un tableau contenant les informations des cartes dans la collection du joueur

function funVente() {
    funClear("content_body");
    var Name = new URLSearchParams(window.location.search).get("Name");
    document.getElementById("content_header").textContent = ('Vente');
    var idUser = JSON.parse(loadRessource(`http://127.0.0.1:${port}/user/name/${Name}`, "GET")).id;
    var listI = JSON.parse(loadRessource(`http://127.0.0.1:${port}/user/getCollection/${idUser}`, "GET"));
    var listCard = []
    listI.forEach(idCard =>
        listCard.push(JSON.parse(loadRessource(`http://127.0.0.1:${port}/card/${idCard}`, "GET"))));
    createFillTabCard(listCard, false);
    funReturnDefault();

}

// Génère le contenu de l'espace jeu
function funJeu() {
    funClear("content_body");
    document.getElementById("content_header").textContent = ('Jeu');
    var ListRoom = JSON.parse(loadRessource(`http://127.0.0.1:${port}/lobby/getAll`, "GET"));
    createFillTabRoom(ListRoom);
    var create = document.createElement("button");
    create.appendChild(document.createTextNode("Create a room"));
    document.getElementById('content_body').appendChild(create);
    create.onclick = function() { select() };
    funReturnDefault();

}

function room(RoomId) {
    funClear("content_body");
    document.getElementById("content_header").textContent = (`Battle room ${RoomId}`);
    var Room = JSON.parse(loadRessource(`http://127.0.0.1:${port}/lobby/${RoomId}`, "GET"));
    if (Room.idPlayer2 == null) {
        document.getElementById("content_body").innerHTML = "Please wait";
        var start = new Date().getTime();
        milliseconds = 1000;
        for (var i = 0; i < 1e7; i++) {
            if ((new Date().getTime() - start) > milliseconds) {
                break;
            }
        }
        room(RoomId)
    } else {
        document.getElementById("content_body").innerHTML = JSON.parse(loadRessource(`http://127.0.0.1:${port}/lobby/Play/${RoomId}`, "GET"));
    }

}

function create(CardId) {
    var Name = new URLSearchParams(window.location.search).get("Name");
    var mise = parseInt(prompt("Votre mise:"));
    var idUser = JSON.parse(loadRessource(`http://127.0.0.1:${port}/user/name/${Name}`, "GET")).id;
    var RoomId = loadRessource(`http://127.0.0.1:${port}/lobby/create/${idUser}/${CardId}/${mise}`, "POST");
    room(RoomId);

}
/* function loadRessource(source, method) {
    var xhttp = new XMLHttpRequest();
    xhttp.open(method, source, true);
    xhttp.overrideMimeType("apPlication/json");
    xhttp.send();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            valueCallBack(this.response);
        }
    };
} */

// Fonction permettant la récupération d'une information située à l'url source avec la méthode method
function loadRessource(source, method) {
    var xhttp = new XMLHttpRequest();
    xhttp.open(method, source, false);
    xhttp.overrideMimeType("apPlication/json");
    xhttp.send();
    if (xhttp.status == 200) {
        return xhttp.response;
    }
};

// Permet l'affichage des liens vers les pages de connexion et d'inscription dans la zone content_header
function funUserDefault() {
    funClear("user_info")

    var login = document.createElement("a");
    login.setAttribute("href", "login.html");
    login.textContent = "Login";
    document.getElementById("user_info").appendChild(login);

    var sub = document.createElement("a");
    sub.setAttribute("href", "sub.html");
    sub.textContent = "Subscribe";
    document.getElementById("user_info").appendChild(sub);
}

// Lorsque l'utilisateur est connecté fait apparaître son nom et sa cagnotte dans la zone content_header
function funUser(Name) {
    funClear("user_info")
    var user = JSON.parse(loadRessource(`http://127.0.0.1:${port}/user/name/${Name}`, "GET"));
    var money = document.createElement("p");
    money.textContent = user.money;
    document.getElementById("user_info").appendChild(money);

    var profil = document.createElement("p");
    profil.textContent = user.name;
    document.getElementById("user_info").appendChild(profil);
}

// Permet de gérer l'achat d'une carte côté client et côté serveur 
function achat(TransactionId) {
    var Name = new URLSearchParams(window.location.search).get("Name");
    var idUser = JSON.parse(loadRessource(`http://127.0.0.1:${port}/user/name/${Name}`, "GET")).id;
    loadRessource(`http://127.0.0.1:${port}/market/buy/${TransactionId}/${idUser}`, "POST");
    funClear(TransactionId);
    document.getElementById(TransactionId).parentElement.remove();
    funUser(Name);
}

// Permet de gérer la vente d'une carte côté client et côté serveur 
function vente(CardId) {
    var Name = new URLSearchParams(window.location.search).get("Name");
    var idUser = JSON.parse(loadRessource(`http://127.0.0.1:${port}/user/name/${Name}`, "GET")).id;
    loadRessource(`http://127.0.0.1:${port}/market/sell/${CardId}/${idUser}`, "POST");
    funClear(CardId);
    document.getElementById(CardId).parentElement.remove();
}


function join(CardId, RoomId) {
    funClear("content_body");
    document.getElementById("content_header").textContent = ('Battle Room');
    var Name = new URLSearchParams(window.location.search).get("Name");
    var idUser = JSON.parse(loadRessource(`http://127.0.0.1:${port}/user/name/${Name}`, "GET")).id;
    loadRessource(`http://127.0.0.1:${port}/lobby/join/${CardId}/${idUser}`, "POST");
    room(RoomId);

}

function select(RoomId) {
    funClear("content_body");
    document.getElementById("content_header").textContent = ('Selection');
    var Name = new URLSearchParams(window.location.search).get("Name");
    var idUser = JSON.parse(loadRessource(`http://127.0.0.1:${port}/user/name/${Name}`, "GET")).id;
    var listI = JSON.parse(loadRessource(`http://127.0.0.1:${port}/user/getCollection/${idUser}`, "GET"));
    var listCard = []
    listI.forEach(idCard =>
        listCard.push(JSON.parse(loadRessource(`http://127.0.0.1:${port}/card/${idCard}`, "GET"))));
    document.getElementById("content_body").innerHTML +=
        `<table style="width:70%" id="tab">
    <tr>
        <th>Name</th>
        <th>Family</th>
        <th>Affinity</th>
        <th>Energy</th>
        <th>HP</th>
    </tr>
</table>`;
    listCard.forEach(Card => {
        if (RoomId == null) {
            document.getElementById("tab").innerHTML +=
                `<tr id="${Card.id}">
        <th>${Card.name}</th>
        <th>${Card.family}</th>
        <th>${Card.affinity}</th>
        <th>${Card.energy}</th>
        <th>${Card.hp}</th>
        <th><button onclick= "create(${Card.id})"> Fight </th>
    </tr>`;
        } else {
            document.getElementById("tab").innerHTML +=
                `<tr id="${Card.id}">
        <th>${Card.name}</th>
        <th>${Card.family}</th>
        <th>${Card.affinity}</th>
        <th>${Card.energy}</th>
        <th>${Card.hp}</th>
        <th><button onclick= "join(${Card.id,RoomId})"> Fight </th>
    </tr>`;
        }
    });
    funReturnDefault();
}

// Permet de créer et de remPlir un tableau contenant les informations sur une liste de cartes
// Si bool vaut true: affiche le tableau des achats
// Sinon affiche celui des ventes
function createFillTabCard(ListCard, bool) {
    document.getElementById("content_body").innerHTML +=
        `<table style="width:70%" id="tab">
        <tr>
            <th>Name</th>
            <th>Family</th>
            <th>Affinity</th>
            <th>Energy</th>
            <th>HP</th>
            <th>Price</th>
        </tr>
    </table>`;
    if (bool) {
        ListCard.forEach(Transaction => {
            var Card = JSON.parse(loadRessource(`http://127.0.0.1:${port}/card/${Transaction.idCard}`, "GET"));
            document.getElementById("tab").innerHTML +=
                `<tr id="${Transaction.id}">
            <th>${Card.name}</th>
            <th>${Card.family}</th>
            <th>${Card.affinity}</th>
            <th>${Card.energy}</th>
            <th>${Card.hp}</th>
            <th>${Card.price}</th>
            <th><button onclick= "achat(${Transaction.id})"> achat </th>
        </tr>`;
        });

    } else {
        ListCard.forEach(Card => {
            document.getElementById("tab").innerHTML +=
                `<tr id="${Card.id}">
            <th>${Card.name}</th>
            <th>${Card.family}</th>
            <th>${Card.affinity}</th>
            <th>${Card.energy}</th>
            <th>${Card.hp}</th>
            <th>${Card.price}</th>
            <th><button onclick= "vente(${Card.id})"> vente </th>
        </tr>`;
        });
    }


}

// Permet de créer et de remPlir un tableau contenant les informations sur une liste de cartes
// Si bool vaut true: affiche le tableau des achats
// Sinon affiche celui des ventes
function createFillTabRoom(ListRoom) {
    document.getElementById("content_body").innerHTML +=
        `<table style="width:70%" id="tab">
        <tr>
            <th>Challenger</th>
            <th>Bet</th>

        </tr>
    </table>`;
    ListRoom.forEach(Room => {
        var Player1 = JSON.parse(loadRessource(`http://127.0.0.1:${port}/user/id/${Room.idPlayer1}`, "GET"));
        document.getElementById("tab").innerHTML +=
            `<tr id="${Room.id}">
        <th>${Player1}</th>
        <th>${Room.mise}</th>
        <th><button onclick= "select(${Room.id})"> Select a Card to fight </th>
    </tr>`;
    });



}

// Lors du chargement de la page:
//si l'utilisateur est connecté alors le contenu_header fait apparaître son nom et sa cagnotte
//sinon le contenu_header contient des liens vers les pages de connexion et d'inscription 
window.onload = function() {
    funDefault();
    var Name = new URLSearchParams(window.location.search).get("Name");
    var Password = new URLSearchParams(window.location.search).get("Password");
    if (Name != null) {
        if (loadRessource(`http://127.0.0.1:${port}/login/${Name}/${Password}`, "GET") == "true") {
            funUser(Name);
        }
    } else {
        funUserDefault();
    }
};

// Si le client n'est pas connecté, le redirige vers la page de connexion
window.onclick = function() {
    var Name = new URLSearchParams(window.location.search).get("Name");
    if (Name == null) {
        window.location.href = "login.html";
    }

};

// Si le client n'est pas connecté, le redirige vers la page de connexion
window.onclick = function() {
    var Name = new URLSearchParams(window.location.search).get("Name");
    if (Name == null) {
        window.location.href = "login.html";
    }

};