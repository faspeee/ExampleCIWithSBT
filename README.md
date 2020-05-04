**Commandi utili**

****Comandi git****

per permettere modificare i login ed evitare _**! [remote rejected] master -> master (permission denied)**_

_git config --global --edit_

e dopo 

`[credential]` 

     `helper = osxkeychain`
     `useHttpPath = true`

per far **pull** del repository originale bisogna scrivere

_git pull [url repo originale]_

per far **pull** del nostro repository bisogna scrivere

_git pull_

****Comandi vim****

per salvare= _**esc+ :w**_ 

per uscire = _**esc + :qa!**_

per inserire testo = _**i**_

