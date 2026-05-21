/**
 * 
 */
let scrollAmount = 0;

function slideRight() {
  const slider = document.getElementById("teamSlider");
  slider.scrollBy({
    left: 300,
    behavior: "smooth"
  });
}

function slideLeft() {
  const slider = document.getElementById("teamSlider");
  slider.scrollBy({
    left: -300,
    behavior: "smooth"
  });
}