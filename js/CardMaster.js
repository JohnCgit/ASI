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
    document.getElementsByClassName("header")[0].innerHTML = ('Achat');
    loadRessource("tab.hmtl","content","GET");  
    funReturnDefault();
}

function funVente() {
    funClear("content");
    loadRessource("tab.hmtl","content","GET");
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

function loadRessource(source,destination,method) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
       document.getElementById(destination).innerHTML = this.responseText;
      }
    };
    xhttp.open(method, source, true);
    xhttp.send();
}

window.onload = function() {
    funDefault();




};