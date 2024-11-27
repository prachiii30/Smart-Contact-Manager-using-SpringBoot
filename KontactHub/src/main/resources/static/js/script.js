console.log("script loaded")

let currentTheme= getTheme();

changeTheme();
function changeTheme(){
    document.querySelector('html').classList.add(currentTheme);

    const change=document.querySelector('#theme_change_button')
    change.addEventListener("click",()=>{
    console.log("change theme button clicked")
    document.querySelector('html').classList.remove(currentTheme);


    if(currentTheme==="light"){
           currentTheme="dark"
    }
    else
    currentTheme="light";

    //update local storange
    setTheme(currentTheme);
    document.querySelector('html').classList.add(currentTheme);

 });


}

//set theme to local storage

function setTheme(theme){
    localStorage.setItem("theme",theme);
}

//get theme from local storage
function getTheme(){
    let theme=localStorage.getItem("theme");
    if(theme)
        return theme;
    else
        return "light";
}