"use strict";
import {byId, toon, setText, verberg,getPrincipal} from "./util.js";
getPrincipal();
let taakId = null;
let naamWerknemer = null;
verberg("hoofd1");
const werknemer = JSON.parse(sessionStorage.getItem("werknemer"));
setText("werknemer", werknemer.familienaam + " " + werknemer.voornaam);
naamWerknemer = werknemer.voornaam +" "+ werknemer.familienaam;
setText("locatieId", werknemer.locatie);
findTakenVanWerknemerOpLocatie(werknemer.id, werknemer.locatieId);




async function findTakenVanWerknemerOpLocatie(id, locatieId) {
    const response = await fetch(`taken/${id}/${locatieId}`);
    if (response.ok) {
        const taken = await response.json();
        if (taken.length > 0) {
            byId('takenBody').innerHTML = "";
            toon("hoofd1");

            for (const taak of taken) {
                const tr = takenBody.insertRow();
                tr.insertCell().textContent = taak.id;
                tr.insertCell().textContent = taak.onderhoudsDatum;
                tr.insertCell().textContent = taak.teller;
                tr.insertCell().textContent = taak.omschrijving;
                tr.insertCell().textContent = taak.machineId;
                tr.insertCell().textContent = taak.maintenanceType;
                tr.insertCell().textContent = taak.mode;
                // tr.insertCell().textContent = taak.status;
                tr.dataset.id = taak.id;
                const td = tr.insertCell();

                td.innerHTML = `
  <button class="btn btn-sm btn-secondary status-btn" data-id="${taak.id}">
    ${taak.status}
  </button>
`;


            }
        } else {
            byId('takenBody').innerHTML = "";
            verberg("hoofd1");

        }
    } else {
        toon("storing");
    }
}
document.addEventListener("click", (e) => {
    const clickedBtn = e.target;

    // Check of het een status-knop is
    if (!clickedBtn.classList.contains("status-btn")) return;

    // Reset alle andere knoppen
    document.querySelectorAll(".status-btn").forEach(btn => {
        btn.textContent = "NIETGEDAAN";
        btn.classList.remove("btn-success");
        btn.classList.add("btn-secondary");
    });

    clickedBtn.textContent = "GEDAAN";
    clickedBtn.classList.remove("btn-secondary");
    clickedBtn.classList.add("btn-success");
    toon("zoek");
     taakId = clickedBtn.dataset.id;

});

byId("zoek").onclick = async function (){

    const taakBevestigenResponse = await fetch(`taken/bevestigen/${taakId}`,
        {
            method: "PUT",
            headers: {'Content-Type': "text/plain"},
            //body: JSON.stringify(naamWerknemer),
            body: naamWerknemer,
        });
    const rij = document.querySelector(`tr[data-id="${taakId}"]`);
    rij.remove(); // Verwijder de rij
    verberg("zoek");



}




