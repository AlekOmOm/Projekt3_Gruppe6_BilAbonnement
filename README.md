# How-To-Handle Guide!

```bash
# Kør applikation på linux server - 1
# Kør applikation på Local Host - 2
# Bemærk! - 3
```

## 1️⃣ Kør applikation på linux server❗
```
0.0: Du ankommer på vores Login side.
0.1: Opret dig selv som bruger.   ( Husk hvilken afdeling du vælger at oprette dig i❗ )
0.2: Log in med dit eget log in.
  ```

  ##  1.0 For Dataregistrering
##### 1.1 Tryk på *Opret en LejeAftale*
##### 1.2 Vælg en Bil ved at trykke på bjælken *vælg bil*. Tryk derefter Vælg Bil-knappen for at gå videre, eller Anuller-knappen for at gå tilbage.
##### 1.3 Tryk på tilvalg til dit abonnement. Tryk derefter på "Opret Abonnement"-knappen, eller Tilbage-knappen for at gå tilbage.
##### 1.4 Tryk på en bjælke for at folde en menu ud. Efter du har taget i valg i begge bjælker tryk derefter på næste-knappen.
##### 1.5 På denne side skal du indtaste alt den nødvendige information. Derefter tryk Bekrlft og gå videre.
##### 1.6 Her skal du vælge en bilforhandler hvor du vil hente bilen. Dette er sidste step. Tryk derefter Opret Leje Aftale
##### 1.7 Nu er lejeaftalen oprettet og gemt i databasen med dine valg.
##### 1.8 Nu kan du logge ud ved at trykke på *log out* knappen.
  ##  2.0 For Skade- og Udbedring
##### 2.1 I midten er der en liste over lejeaftaler (nyeste oprettet er altid nederst). Tryk *Generer Rapport*. Du kan vælge din eller de andre.
##### 2.2 Her er det vigtigt at du først markere hvilke skader du vil have med i din skaderapport og derefter trykke *Refresh.* 
##### 2.3 Bagefter skal du vælge hvor mange kilometer kørt over, og derefter kan du trykke *Generer Rapport*
##### 2.4 Du får vist din skaderapport, Raportens ID, Brugerens ID, Kilometer Kørt Over og Reparationsomkostninger. Tryk derefter *Tilbage til oversigt*
##### 2.5 Du er kommet tilbage til Skade- og Udbedrings oversigten, hvor du så kan se at din rapport er rykket over i arkivet og gemt der. Du kan trykke *Se rapport* hvis du vil se din skaderapport.
##### 2.6 Tryk nu *log out* for at komme til login siden igen.
   ## 3.0 For Forretnings udvikling
##### 3.1 Her for du vist antal udlejede biler og total indkomst for udlejede biler.
##### 3.2 Så kan du trykke *Generer Rapport* som generer en rapport, og viser dig den genererede forretningsrapport.
##### 3.3 Ved at trykke *tilbage*, kan du så se at der er blevet gemt en rapport i arkivet til højre, som har gemt dataen om udlejede biler og total indkomst i en rapport.
##### 3.4 Tryk herefter *log out* for at komme tilbage.





# 3. Bemærk!

## Samme brugernavn

  ##### Hvis der oprettes to brugere med samme brugernavn, men med forskellige roller, vil databasen kun acceptere det første brugernavn. 

  ##### Forsøg på at logge ind med det andet brugernavn vil resultere i en redirect til login-siden, men det mislykkede login vil stadig blive registreret i databasen.
  
### Der kan forekomme andre uforudsete fejl!
