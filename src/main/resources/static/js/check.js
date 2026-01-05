"use strict";
import {byId, toon, setText, verberg} from "./util.js";
verberg("hoofd1");
byId("datumKnop").onclick = async () =>{
    const datumInvoer = byId("datum");
    if (!datumInvoer.checkValidity()){
        toon("datumFout");
        verberg("hoofd1");
    }
    else {
        verberg("datumFout");

    }
    const aantalFouten = document.querySelectorAll(".fout:not([hidden])").length;
    if (aantalFouten === 0) {
        toonTakenVanaf(datumInvoer.value);
    }
    else {
        byId("takenBody").innerHTML = "";
    }
}

async function toonTakenVanaf (datum) {
    const takenVanafResponse = await fetch(`taken?datum=${datum}`);
    if (takenVanafResponse.ok) {
        byId("takenBody").innerHTML = "";
        const takenlijst = await takenVanafResponse.json();
        if (takenlijst.length > 0) {
            toon("hoofd1");
            verberg("datumFout");
            for (const taak of takenlijst) {
                const tr = takenBody.insertRow();
                tr.insertCell().textContent = taak.id;
                tr.insertCell().textContent = taak.onderhoudsDatum;
                tr.insertCell().textContent = taak.teller;
                tr.insertCell().textContent = taak.omschrijving;
                tr.insertCell().textContent = taak.machineId;
                tr.insertCell().textContent = taak.status;
                tr.insertCell().textContent = taak.lastPerson;
            }
        }
    }
}
