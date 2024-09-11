#Scopo dell'applicativo


L'applicazione deve permettere la registrazione dei utenti tramite diverse api:
  /register (per registrarsi)
  /LoginStandard (per fare il login)
  /LoginGithub
  /loginWithMicrosoft
  /loginWithGoogle

Come metodo di autenticazione base usare HS256 (chiave simmetrica, quindi una sola chiave privata nel backend)

L'applicazione deve peremttere l'aggiornamento e la cencellazione logica dei utenti dai dabase.

Per lo sviluppo del database su utilizza JPA con annotazione, evitiamo la scrittura del linguaggio sql.

La piattaforma ha come scopo quello di essere un microservizio autonomo per la gestione e registrazione dei utenti.
