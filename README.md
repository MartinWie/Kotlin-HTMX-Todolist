# Kotlin-HTMX-Todolist

In my KotlinCodeJourney repo I tested the basics of Kotlin + HTMX this repo is to deepen this experiment and later on
create a template for full stack kotlin projects.

## Build the project

### Install tooling:

Tailwind: for CSS generation
Browser-sync: auto refresh browser on changes, for details checkout 'startServer.sh'
Entr: trigger action on file change, for details checkout 'startServer.sh'

```Terminal
npm install -D tailwindcss
npm install -g browser-sync 
brew install entr
```

### Live development

Run the following command/script

```Terminal
bash buildAndReloadBrowserOnsave.sh
```

For details please read the scripts, but here is the sort summary:
The script uses entr to trigger another script on every *.kt file change.
(entr will exit if new files are created, this is the reason for the loop, for details read the man page of entr)
The second script (start server) builds our CSS and runs the app server with gradle.
After a certain log stage is reached the script starts/refreshes a browser session with browser-sync to live update the
page.
If startServer script receives an user exit, we run a stop script and break out of the loop to exit the
buildAndReloadBrowserOnsave.sh script.

## Project todos( future optimisation will probably not do :D ):

- Setup tailwind theme or at least make todos not look ugly
- Make use of html templates instead of fully utilizing kotlin dsl to safe CPU on constructing the page every time

## Support me :heart: :star: :money_with_wings:

If this project provided value, and you want to give something back, you can give the repo a star or support me, by
tipping me a coffee.

<a href="https://buymeacoffee.com/MartinWie" target="_blank"><img src="https://cdn.buymeacoffee.com/buttons/v2/default-blue.png" alt="Buy Me A Coffee" width="170"></a>
