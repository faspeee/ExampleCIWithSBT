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

per **eliminare** un file bisogna scrivere 

git rm _[name_file]_

per **eliminare** una directory bisogna scrivere 

git rm -r _[name_directory]_

per vedere stato git 

git status

per resolvere merge conflict guardare il seguente link

https://stackoverflow.com/questions/161813/how-to-resolve-merge-conflicts-in-git

evitare creare file .orig

_git config --global mergetool.keepBackup false_

per fare un upstream il cui ci permette essere aggiornato col repository principale bisogna
fare: 

_**git checkout master**_

**_git checkout -b mybranch_**

**_git remote add upstream /url/original/repo_**

**_git fetch upstream_**

per rimuovere un upstream bisogna scrivere 
**_git remote rm upstream_**
****Comandi vim****

per salvare= _**esc+ :w**_ 

per uscire = _**esc + :qa!**_

per inserire testo = _**i**_

