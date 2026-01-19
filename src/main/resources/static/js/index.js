"use strict";
import {byId, toon,verberg,getPrincipal} from "./util.js";
const checklink = document.getElementById("checklink");
const zoekknop = document.getElementById("zoek");
getPrincipal().then(async () => {
    const username = document.getElementById("userName");
    const lognaamResponse = await fetch(`users/1`);
    if (lognaamResponse.ok) {
        const logname = await lognaamResponse.text();
        if (username.textContent.trim() === logname.trim()) {
            checklink.classList.add("disabled");
        } else {
            zoekknop.classList.add("disabled")
        }
    }
    ;
});
    byId("zoek").onclick = async () => {
        verberg("naamFout", "storing", "conflict", "badRequest", "mededeling");
        toon("hoofd1");
        const naamInput = byId("naam");
        if (!naamInput.checkValidity()) {
            toon("naamFout");
            verberg("hoofd1");

        }
        const aantalFouten = document.querySelectorAll(".fout:not([hidden])").length;
        if (aantalFouten === 0 && naamInput.checkValidity()) {
            zoek(naamInput.value);
        } else {
            byId("werknemersBody").innerHTML = "";
        }

    }

    async function zoek(woord) {
        const response = await fetch(`werknemers?naamBevat=${woord}`);
        if (response.ok) {
            byId("werknemersBody").innerHTML = "";
            const werknemers = await response.json();
            if (werknemers.length > 0) {
                verberg("naamFout", "storing", "conflict", "badRequest", "mededeling");
                for (const werknemer of werknemers) {
                    const tr = werknemersBody.insertRow();
                    tr.insertCell().textContent = werknemer.familienaam;
                    tr.insertCell().textContent = werknemer.voornaam;
                    const td = tr.insertCell();
                    const hyperlink = document.createElement("a");
                    //originele paginareferentie
                    //   hyperlink.href = "takenlijst.html";
                    // verwijzing naar de arduinoCheck
                    hyperlink.href = "extracheck.html";
                    hyperlink.textContent = werknemer.locatie;
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
        } else {
            const username = byId("userName");
            const lognaamResponse = await fetch(`users/2`);
            if (lognaamResponse.ok) {
            const lognaam = await lognaamResponse.text();
            if (username.textContent === lognaam.trim()) {
                toon("loginNok");
                toon("loginknop");
                verberg("hoofd1");
                byId("werknemersBody").textContent = "";
            } else {
                toon("storing");
                verberg("hoofd1");
                byId("werknemersBody").textContent = "";
            }
        }
        }
    }

    window.onload = () => {
        const knop = document.getElementById("ing");
        knop.onclick = () => {
            window.location.href = "check.html";
        };
    };

    const tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]');
    const tooltipList = [...tooltipTriggerList].map(el => new bootstrap.Tooltip(el));