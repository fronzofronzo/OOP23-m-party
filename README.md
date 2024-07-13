![Mario Party](./Mario_Party_Logo_(MPSuperstars).png)

# Obiettivo del Progetto

Il gruppo si pone come obiettivo quello di realizzare un gioco che prende ispirazione dalla saga "Mario Party" ([link](https://it.wikipedia.org/wiki/Mario_Party_(serie))).

Il gioco è per 2-4 giocatori. Ogni giocatore ha un certo numero di stelle (necessarie per vincere la partita) e un certo numero di monete (necessarie per acquistare oggetti dal negozio per acquistare stelle). I player si muovono all'interno di un tabellone di gioco in cui ogni casella può essere:

- **Mini Gioco singleplayer**: minigioco in cui uno dei giocatori si deve destreggiare individualmente per raccogliere monete;
- **Mini Gioco multiplayer**: due giocatori si sfidano e il vincente guadagna un bonus mentre il perdente perde una malus;
- **Bonus/Malus**: il giocatore che finisce su una di queste caselle può vedersi ottenere un bonus o un malus;
- **Stella**: il giocatore che capita su una di queste caselle può acquistare (nel caso in cui abbia abbastanza monete) una stella;
- **Negozio**: il player può acquistare item (es. potenziamenti) con le monete guadagnate durante la partita.

La partita è costituita da un certo numero di turni (es. 10) al termine dei quali il giocatore vincente è quello che avrà il maggior numero di stelle.

## Funzionalità minimi ritenute obbligatorie:

- Realizzazione di mini giochi singleplayer: implementazione di logica e grafica di gioco;
- Realizzazione di mini giochi multiplayer (2 giocatori): implementazione di logica del gioco e grafica;
- Implementazione e gestione della partita (player, monete per player, stelle);
- Implementazione di item che forniscono bonus o malus ai giocatori;
- Implementazione negozio di gioco.

## "Challenge" principali:

- Realizzazione del progetto utilizzando in modo adeguato i principi del pattern MVC;
- Utilizzo di Git;
- Coordinazione lavoro di gruppo;
- Utilizzo della libreria JavaFX per costruzione dell'interfaccia grafica;
- Mantenere il codice estendibile.

## Funzionalità opzionali:

- Assegnazione randomica delle caselle del tabellone di gioco al fine di ottenere una configurazione diversa ad ogni partita;
- Gestione di statistiche della partita e schermata in cui è possibile visualizzare queste ultime con eventuali bonus di fine partita;

## Suddivisione del lavoro:

- **Yan**: Implementazione di logica e grafica di 2 minigiochi (uno multiplayer e uno singleplayer); implementazione della schermata di fine partita e gestione del tabellone;
- **Fronzoni**: Implementazione di un minigioco singleplayer; implementazione di Player, monete e Stelle;
- **Ary**: Implementazione di logica e grafica di 2 minigiochi (uno multiplayer e uno singleplayer); implementazione della schermata di inizio partita (menu);
- **Frenk**: Implementazione di un mini gioco; implementazione e gestione del negozio e relativi items;
- **Real**: Implementazione di un mini gioco multiplayer; implementazione e gestione del tabellone

Le eventuali funzionalità opzionali verranno suddivise col procedere dello stato del progetto.
