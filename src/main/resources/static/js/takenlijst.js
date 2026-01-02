"use strict";
import {byId, toon, setText,verberg} from "./util.js";
verberg("hoofd1");
const werknemer =JSON.parse(sessionStorage.getItem("werknemer"));
setText("werknemer", werknemer.familienaam +" "+ werknemer.voornaam);
setText("locatieId",werknemer.locatieId)
findTakenVanWerknemerOpLocatie(werknemer.id, werknemer.locatieId);


async function findTakenVanWerknemerOpLocatie(id, locatieId) {
    const response = await fetch(`taken/${id}/${locatieId}`);
    if (response.ok) {
        const taken = await response.json();
        if (taken.length>0) {
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
                const td = tr.insertCell();

                td.innerHTML = `
  <div class="dropdown">
    <button class="btn btn-sm btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown">
      ${taak.status}
    </button>
    <ul class="dropdown-menu">
      <li><a class="dropdown-item" href="#">NIETGEDAAN</a></li>
      <li><a class="dropdown-item" href="#">BEZIG</a></li>
      <li><a class="dropdown-item" href="#">GEDAAN</a></li>
    </ul>
  </div>
`;
            }
        }
        else{
            byId('takenBody').innerHTML = "";
            verberg("hoofd1");

        }
    }else{
        toon("storing");
    }
}