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
    //createFillTab(loadRessource(/Market/GetALL))

    funReturnDefault();
}

function funVente() {
    funClear("content");
    document.getElementsByClassName("header")[0].innerHTML = ('Vente');
    //createFillTab(loadRessource("/User/GetCollection","GET"))
    var List = [
        { 
            "Name": "test",
            "Family":"blabla",
            "Affinity": "hfh",
            "Energy":50,
            "HP":100,
            "Price":250
        },
        { 
            "Name": "test",
            "Family":"blabla",
            "Affinity": "hfh",
            "Energy":50,
            "HP":100,
            "Price":250
        } 
    ];
    console.log(List);
    createFillTab(List);
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

function loadRessource(source,method) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
        return this.response;
      }
    };
    xhttp.open(method, source, true);
    xhttp.send();
}

function createFillTab(ListCard){
    var tab = document.createElement("tab");
    tab.innerHTML+=
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
    for(const Card in ListCard){
        console.log(Card);
        tab.innerHTML += 
        `<tr>
            <th>${Card.Name}</th>
            <th>${Card.Family}</th>
            <th>${Card.Affinity}</th>
            <th>${Card.Energy}</th>
            <th>${Card.HP}</th>
            <th>${Card.Price}</th>
        </tr>`;
    }
    document.getElementById("content").appendChild(tab);
    
}


window.onload = function() {
    funDefault();




};