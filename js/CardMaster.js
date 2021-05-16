function funClear(arg) {
    myNode = document.getElementById(arg);
    while (myNode.firstChild) {
        myNode.removeChild(myNode.lastChild);
    }
}

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

function funReturnDefault() {
    var def = document.createElement("button");
    def.appendChild(document.createTextNode('Retour arrière'));
    document.getElementById('content_body').appendChild(def);
    def.onclick = funDefault;

}

function funAchat() {
    funClear("content_body");
    document.getElementById("content_header").textContent = ('Achat');
    //createFillTab(loadRessource(/Market/GetALL))
    createFillTab(JSON.parse(loadRessource("ListCard.json", "GET")));
    funReturnDefault();
}

function funVente() {
    funClear("content_body");
    document.getElementById("content_header").textContent = ('Vente');
    //createFillTab(loadRessource("/User/GetCollection","GET"))
    createFillTab(JSON.parse(loadRessource("ListCard.json", "GET")));
    funReturnDefault();

}

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

function loadRessource(source, method) {
    var xhttp = new XMLHttpRequest();
    xhttp.open(method, source, false);
    xhttp.overrideMimeType("application/json");
    xhttp.send();
    if (xhttp.status == 200) {
        return xhttp.response;
    }
};


function funUserDefault() {

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

function funUser(Surname, Password) {
    var user = { money: 500, Name: "Maurice" };

    var money = document.createElement("p");
    money.textContent = user.money;
    document.getElementById("user_info").appendChild(money);

    var profil = document.createElement("p");
    profil.textContent = user.Name;
    document.getElementById("user_info").appendChild(profil);
}

function createFillTab(ListCard) {
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
    ListCard.forEach(Card => {
        document.getElementById("tab").innerHTML +=
            `<tr>
        <th>${Card.Name}</th>
        <th>${Card.Family}</th>
        <th>${Card.Affinity}</th>
        <th>${Card.Energy}</th>
        <th>${Card.HP}</th>
        <th>${Card.Price}</th>
    </tr>`;
    });

}

window.onload = function() {
    funDefault();
    var Surname = new URLSearchParams(window.location.search).get("Surname");
    var Password = new URLSearchParams(window.location.search).get("Password");
    //if loadRessource(`/verifUser/${Surname}/${Password}`,'GET') = true
    if (Surname != null) {
        funUser(Surname, Password);
    } else {
        funUserDefault();
    }
};