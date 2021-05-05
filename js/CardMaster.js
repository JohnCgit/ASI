function funClear(arg) {
    myNode = document.getElementById(arg);
    while (myNode.firstChild) {
        myNode.removeChild(myNode.lastChild);
    }
}

function funDefault() {
    funClear("header");
    funClear("content");
    var achat = document.createElement("button");
    achat.appendChild(document.createTextNode('div achat'));


    var vente = document.createElement("button");
    vente.appendChild(document.createTextNode('div vente'));

    var jeu = document.createElement("button");
    jeu.appendChild(document.createTextNode('div jeu'));

    document.getElementById('content').appendChild(achat);
    document.getElementById('content').appendChild(vente);
    document.getElementById('content').appendChild(jeu);
    achat.onclick = funAchat;
    vente.onclick = funVente;
    jeu.onclick = funJeu;
}

function funReturnDefault() {
    var def = document.createElement("button");
    def.appendChild(document.createTextNode('Retour arrière'));
    document.getElementById('content').appendChild(def);
    def.onclick = funDefault;

}

function funAchat() {
    funClear("content");
    var achat = document.createElement("div");
    achat.appendChild(document.createTextNode('Réussi achat'));

    document.getElementById('content').appendChild(achat);
    document.getElementsByClassName("header")[0].innerHTML = ('Achat');
    funReturnDefault();
}

function funVente() {
    funClear("content");
    var vente = document.createElement("div");
    vente.appendChild(document.createTextNode('Réussi vente'));

    document.getElementById('content').appendChild(vente);
    document.getElementsByClassName("header")[0].innerHTML = ('Vente');
    funReturnDefault();

}

function funJeu() {
    funClear("content");
    var jeu = document.createElement("div");
    jeu.appendChild(document.createTextNode('Réussi jeu'));

    document.getElementById('content').appendChild(jeu);
    document.getElementsByClassName("header")[0].innerHTML = ('Jeu');
    funReturnDefault();

}



window.onload = function() {
    funDefault();




};