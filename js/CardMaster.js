var port = 8083;
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
    achat.appendChild(document.createTextNode('div achat'));

    var vente = document.createElement("button");
    vente.appendChild(document.createTextNode('div vente'));

    var jeu = document.createElement("button");
    jeu.appendChild(document.createTextNode('div jeu'));

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
    //createFillTab(loadRessource(/Market/GetALL))
    createFillTab(JSON.parse(loadRessource(`http://127.0.0.1:${port}/market/getAll`, "GET")), true);
    funReturnDefault();
}

// Génère le contenu de l'espace vente:
// Un tableau contenant les informations des cartes dans la collection du joueur

function funVente() {
    funClear("content_body");
    document.getElementById("content_header").textContent = ('Vente');
    var idUser = JSON.parse(loadRessource(`http://127.0.0.1:${port}/user/name/${Name}`, "GET")).id;
    createFillTab(JSON.parse(loadRessource(`http://127.0.0.1:${port}/user/getAllCard/${idUser}`, "GET")), false);
    funReturnDefault();

}

// Génère le contenu de l'espace jeu
function funJeu() {
    funClear("content_body");
    var jeu = document.createElement("div");
    jeu.appendChild(document.createTextNode('Réussi jeu'));

    document.getElementById('content_body').appendChild(jeu);
    document.getElementById("content_header").textContent = ('Jeu');
    funReturnDefault();

}

/* function loadRessource(source, method) {
    var xhttp = new XMLHttpRequest();
    xhttp.open(method, source, true);
    xhttp.overrideMimeType("application/json");
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
    xhttp.overrideMimeType("application/json");
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

/* function funUser(Surname, Password) {
    var user = JSON.parse(loadRessource(`/getUser/${Surname}/${Password}`), 'GET');

    var money = document.createElement("p");
    money.textContent = user.money;
    document.getElementById("user_info").appendChild(money);

    var profil = document.createElement("p");
    profil.textContent = user.Name;
    document.getElementById("user_info").appendChild(profil);
} */

// Lorsque l'utilisateur est connecté fait apparaître son nom et sa cagnotte dans la zone content_header
function funUser(Surname, Password) {
    funClear("user_info")
    var user = JSON.parse(loadRessource(`http://127.0.0.1:${port}/user/name/${Name}`, "GET"));
    var money = document.createElement("p");
    money.textContent = user.money;
    document.getElementById("user_info").appendChild(money);

    var profil = document.createElement("p");
    profil.textContent = user.Name;
    document.getElementById("user_info").appendChild(profil);
}

// Permet de gérer l'achat d'une carte côté client et côté serveur 
function achat(TransactionId) {
    var Surname = new URLSearchParams(window.location.search).get("Surname");
    var Password = new URLSearchParams(window.location.search).get("Password");
    var idUser = JSON.parse(loadRessource(`http://127.0.0.1:${port}/user/name/${Name}`, "GET")).id;
    loadRessource(`http://127.0.0.1:${port}/market/buy/${TransactionId}/${idUser}`, "POST");
    funClear(TransactionId);
    document.getElementById(TransactionId).parentElement.remove();
    funUser();
}

// Permet de gérer la vente d'une carte côté client et côté serveur 
function vente(CardId) {
    var Name = new URLSearchParams(window.location.search).get("Name");
    var idUser = JSON.parse(loadRessource(`http://127.0.0.1:${port}/user/name/${Name}`)).id;
    loadRessource(`http://127.0.0.1:${port}/market/sell/${CardId}/${idUser}`, "POST");
    funClear(CardId);
    document.getElementById(CardId).parentElement.remove();
}
// Permet de créer et de remplir un tableau contenant les informations sur une liste de cartes
// Si bool vaut true: affiche le tableau des achats
// Sinon affiche celui des ventes
function createFillTab(ListCard, bool) {
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
            document.getElementById("tab").innerHTML +=
                `<tr id="${Transaction.id}">
            <th>${Transaction.Card.Name}</th>
            <th>${Transaction.Card.Family}</th>
            <th>${Transaction.Card.Affinity}</th>
            <th>${Transaction.Card.Energy}</th>
            <th>${Transaction.Card.HP}</th>
            <th>${Transaction.Card.Price}</th>
            <th><button onclick= "achat(${Transaction.id})"> achat </th>
        </tr>`;
        });

    } else {
        ListCard.forEach(Card => {
            document.getElementById("tab").innerHTML +=
                `<tr id="${Card.id}">
            <th>${Card.Name}</th>
            <th>${Card.Family}</th>
            <th>${Card.Affinity}</th>
            <th>${Card.Energy}</th>
            <th>${Card.HP}</th>
            <th>${Card.Price}</th>
            <th><button onclick= "vente(${Card.id})"> vente </th>
        </tr>`;
        });
    }


}

// Lors du chargement de la page:
//si l'utilisateur est connecté alors le contenu_header fait apparaître son nom et sa cagnotte
//sinon le contenu_header contient des liens vers les pages de connexion et d'inscription 
window.onload = function() {
    funDefault();
    var Name = new URLSearchParams(window.location.search).get("Surname");
    var Password = new URLSearchParams(window.location.search).get("Password");
    if (Surname != null) {
        if (loadRessource(`http://127.0.0.1:${port}/login/${Name}/${Password}`) == "true") {
            funUser(Surname, Password);
        }
    } else {
        funUserDefault();
    }
};

// Si le client n'est pas connecté, le redirige vers la page de connexion
window.onclick = function() {
    var Name = new URLSearchParams(window.location.search).get("Surname");
    var Password = new URLSearchParams(window.location.search).get("Password");
    //if loadRessource(`/verifUser/${Surname}/${Password}`,'GET') = true
    if (Surname == null) {
        window.location.href = "login.html";
    }

};