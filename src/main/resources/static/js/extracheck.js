"use strict";
import {byId, toon, setText, verberg,getPrincipal} from "./util.js";
getPrincipal();
verberg("badgeAansluit","badgeCommunicatie","badgeResponse","sessionNaam","badgeGoed","badgeNietGoed");
toon("badgeAansluit");
await wachtTotArduinoAangesloten();
const werknemer = JSON.parse(sessionStorage.getItem("werknemer"));
const response = await fetch(`serial/start`);
toon("badgeCommunicatie");
if (response.ok) {
    const naam = await response.text();
    //alert(naam);
    toon("badgeResponse");
    setText("badgenaam", naam);
    toon("sessionNaam");
    setText("gebruikersnaam", werknemer.voornaam);
    if (naam === werknemer.voornaam) {
        toon("badgeGoed");
        setTimeout(() => {
            window.location.href = "takenlijst.html";
        }, 3000);

    }
    else {
        toon("badgeNietGoed")
        setTimeout(() => {
            window.location.href = "login";
        }, 3000);
    }
}


async function wachtTotArduinoAangesloten() {
    while (true) {
        const response = await fetch("serial/connectie");
        const status = await response.text();

        if (status === "poortOk") {
            console.log("Arduino aangesloten");
            return true;
        }

        console.log("Arduino niet aangesloten, opnieuw proberen...");
        await new Promise(resolve => setTimeout(resolve, 1000)); // 1 sec pauze
    }
}