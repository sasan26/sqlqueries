
function showMenu() {
var mobMenu = document.getElementById("headerMenu");
if (mobMenu.className == "topHeaderMenu") {
    mobMenu.className = "topHeaderMenu mobileMenu";
} else {
    mobMenu.className = "topHeaderMenu";
}
}


function tab(evt, menuId) {

    menul = document.getElementsByClassName("menu-l");
    menud = document.getElementsByClassName("menu-d");
    for (i = 0; i < menul.length; i++) {
    menul[i].classList.remove("active");
    }
    for (i = 0; i < menud.length; i++) {
        menud[i].style.display = "none";
        }
    evt.currentTarget.className += " active";
    document.getElementById(menuId).style.display = "block";
}

window.addEventListener('load', (event) => {
    var acc = document.getElementsByClassName("acc");

    for (i = 0; i < acc.length; i++) {
        acc[i].addEventListener("click", function() {
            this.classList.toggle("active");
            var panel = this.nextElementSibling;
            if (panel.style.maxHeight) {
            panel.style.maxHeight = null;
            } else {
            panel.style.maxHeight = panel.scrollHeight + "px";
            } 
        });
    }

  });

