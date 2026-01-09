"use strict";
import {getPrincipal, toon, setText, byId, verberg} from "./util.js";

getPrincipal();
toonlogging();
var date = new Date();
setText("date", date.toISOString());
const werknemer = JSON.parse(sessionStorage.getItem("werknemer"));
setText("werknemer", werknemer.voornaam + " " + werknemer.familienaam);

byId("schrijf").onclick = async () => {
    const werknemerDatumLogging = {
        datumEnTijd: date.toISOString(),
        persoon: werknemer.voornaam + " " + werknemer.familienaam,
        logging: byId("logging").value
    };
    const loggingResponse = await fetch("loggingen",
        {
            method: "POST",
            headers: {'Content-Type': "application/json"},
            body: JSON.stringify(werknemerDatumLogging)
        });
    if (loggingResponse.ok) {
        verberg("schrijf");
        byId("logging").value ="";
        byId("remainingzin").innerText="Logging weggeschreven.";
    }
};
async function toonlogging() {
    const loggingResponse = await fetch("loggingen");
    if (loggingResponse.ok) {
        const loggingen = await loggingResponse.json();
        const loggingBody = byId("loggingBody");
        for (const logging of loggingen) {
            const tr =loggingBody.insertRow();
            tr.insertCell().textContent = logging.id;
            tr.insertCell().textContent = logging.datumEnTijd;
            tr.insertCell().textContent = logging.logging;
            tr.insertCell().textContent = logging.persoon;
        }
    }
}

async function toonloggingTussendatums(van,tot) {
    const toonloggingResponse = await fetch(`loggingen?van=${van}&tot=${tot}`);
    if (toonloggingResponse.ok) {
        const loggingen = await toonloggingResponse.json();
        const loggingBody = byId("loggingBody");
        for (const logging of loggingen) {
            const tr = loggingBody.insertRow();
            tr.insertCell().textContent = logging.id;
            tr.insertCell().textContent = logging.datumEnTijd;
            tr.insertCell().textContent = logging.logging;
            tr.insertCell().textContent = logging.persoon;
        }
    }
}
byId("vernieuw").onclick = async () => {
    byId("loggingBody").innerHTML = "";
    const datumVan = byId("vanDatum");
    const datumTot = byId("totDatum");
    toonloggingTussendatums(datumVan.value, datumTot.value);
}

const input = document.getElementById("totDatum");
const inputVan = document.getElementById("vanDatum");

const vandaag = new Date();
const vandaag2 = new Date();

// morgen
vandaag.setDate(vandaag.getDate() + 1);

// 10 dagen geleden
vandaag2.setDate(vandaag2.getDate() - 10);

// omzetten naar yyyy-mm-dd
const morgen = vandaag.toISOString().split("T")[0];
const tienDagenGeleden = vandaag2.toISOString().split("T")[0];

input.value = morgen;
inputVan.value = tienDagenGeleden;

const textarea = document.getElementById("logging");
const remaining = document.getElementById("remaining");

textarea.addEventListener("input", () => {
    const max = textarea.maxLength;
    const used = textarea.value.length;
    remaining.textContent = max - used;
});



