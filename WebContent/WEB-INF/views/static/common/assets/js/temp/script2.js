document.addEventListener("DOMContentLoaded", function () {
        window.onscroll = function () {
          stickyNav();
        };
  
        var navbar = document.getElementById("navbar");
        var header = document.getElementById("header-sec");
        var sticky = navbar.offsetTop;
  
        function stickyNav() {
          if (window.pageYOffset >= sticky) {
            navbar.classList.add("sticky");
            header.classList.add("sticky-header");
          } else {
            navbar.classList.remove("sticky");
            header.classList.remove("sticky-header");
          }
        }
      });




function redirectToLink(link) {

	var donateL = document.getElementById("donatePopup");
	donateL.url = link;
	openPopup('donate');

}




function redirectToUserProfile() {
	window.location.href = '${pageContext.request.contextPath}/v2/detail';
}



