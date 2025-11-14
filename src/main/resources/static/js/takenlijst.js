"use strict";
import {byId, toon, setText,verberg} from "./util.js";
const werknemer =JSON.parse(sessionStorage.getItem("werknemer"));
setText("werknemer", werknemer.familienaam +" "+ werknemer.voornaam);
byId("zoekTaken").onclick = async () =>{
    verberg("dagFout");
    const dagInput = byId("getal");
    if (!dagInput.checkValidity()) {
        toon("dagFout");
    }
    const keuzeInput = document.querySelector('input[name="kies"]:checked').value;

    const aantalFouten = document.querySelectorAll(".fout:not([hidden])").length;
    if (aantalFouten === 0) {
        verberg("dagFout");
        findTakenVanLocatieModusDatum(werknemer.locatieId, dagInput.value, keuzeInput);
    }

};

async function findTakenVanLocatieModusDatum(werknemerLocatie, aantalDagen, keuze) {
    const response = await fetch(`taken?vanLocatie=${werknemerLocatie}&aantalDagen=${aantalDagen}&keuze=${keuze}`);
    if (response.ok) {

    }else{
        toon("storing");
    }
}