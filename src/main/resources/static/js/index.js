"use strict";
import {byId, toon, setText,verberg} from "./util.js";

byId("zoek").onclick = async () =>{
    verberg("naamFout", "storing", "conflict","badRequest","mededeling");
    toon("hoofd1");
    const naamInput = byId("naam");
    if (!naamInput.checkValidity()) {
        toon("naamFout");
        verberg("hoofd1");
    }
    const aantalFouten = document.querySelectorAll(".fout:not([hidden])").length;
    if (aantalFouten === 0) {
        zoek(naamInput.value);
    }

}

async function zoek (woord){
    const response = await fetch(`werknemers?naamBevat=${woord}`);
    if (response.ok) {
        werknemersBody.innerHTML = "";
        const werknemers = await response.json();
        if (werknemers.length > 0) {
            for (const werknemer of werknemers) {
                const tr = werknemersBody.insertRow();
                tr.insertCell().textContent = werknemer.familienaam;
                tr.insertCell().textContent = werknemer.voornaam;
                const td = tr.insertCell();
                const hyperlink = document.createElement("a");
                hyperlink.href = "takenlijst.html";
                hyperlink.textContent= werknemer.locatieId;
                hyperlink.onclick = () => {
                    sessionStorage.setItem("werknemer", JSON.stringify(werknemer));
                }
                td.appendChild(hyperlink);
            }
        }
        if (werknemers.length == 0) {
            verberg("hoofd1");
            toon("mededeling");
        }
    }
    else{
        toon("storing")
    }
}