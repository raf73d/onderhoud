"use strict";
import {byId, toon, setText, verberg} from "./util.js";
verberg("hoofd1");
byId("datumKnop").onclick = async () =>{
    verberg("statusBevestigen");
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

byId("statusBevestigen").onclick = async () =>{
    const taakId = JSON.parse(sessionStorage.getItem("taakid"));
    const taakAanpassing = await fetch(`taken/${taakId}/aanpassen`,
        {method: "PUT",
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify("Engineer " +new Date().toISOString())}
                                              );
    if(taakAanpassing.ok){
    verberg("statusBevestigen");

        const table = byId("takenBody");
        const rij = [...takenBody.rows].find(r => r.dataset.id === String(taakId));
        if (rij) rij.remove();



    }
    else
    {
        toon("backuptaakFout");
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
                tr.dataset.id = taak.id;
                //tr.insertCell().textContent = taak.id;
                const td = tr.insertCell();
                const hyperlink = document.createElement("a");
                hyperlink.textContent= taak.id;
                hyperlink.href = `#`;
                hyperlink.onclick = () => {
                    sessionStorage.setItem("taakid", JSON.stringify(taak.id));
                    toon("statusBevestigen");
                    toon("taakidRollback");
                    setText("taakidRollback", taak.id);
                    verberg("backuptaakFout");
                //    sessionStorage.setItem("taakRowIndex", tr.rowIndex);

                }
                td.appendChild(hyperlink);
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
