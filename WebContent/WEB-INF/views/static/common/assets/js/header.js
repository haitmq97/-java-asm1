window.onscroll = function() {myFunction()};

var navbar = document.getElementById("navbar");
var header = document.getElementById("header");
var sticky = navbar.offsetTop;

function myFunction() {
  if (window.pageYOffset >= sticky) {
    navbar.classList.add("sticky")
    header.classList.add("sticky-header");
  } else {
    navbar.classList.remove("sticky");
    header.classList.remove("sticky-header");
  }
}