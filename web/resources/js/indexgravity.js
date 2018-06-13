var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

var Particle = function () {
  function Particle(svg, coordinates, friction) {
    _classCallCheck(this, Particle);

    this.svg = svg;
    this.steps = $(window).height() / 2;
    this.item = null;
    this.friction = friction;
    this.coordinates = coordinates;
    this.position = this.coordinates.y;
    this.dimensions = this.render();
    this.rotation = Math.random() > 0.5 ? "-" : "+";
    this.scale = 0.5 + Math.random();
    this.siner = 200 * Math.random();
  }

  _createClass(Particle, [{
    key: "destroy",
    value: function destroy() {
      this.item.remove();
    }
  }, {
    key: "move",
    value: function move() {
      this.position = this.position - this.friction;
      var top = this.position;
      var left = this.coordinates.x + Math.sin(this.position * Math.PI / this.steps) * this.siner;
      this.item.css({
        transform: "translateX(" + left + "px) translateY(" + top + "px) scale(" + this.scale + ") rotate(" + this.rotation + (this.position + this.dimensions.height) + "deg)"
      });

      if (this.position < -this.dimensions.height) {
        this.destroy();
        return false;
      } else {
        return true;
      }
    }
  }, {
    key: "render",
    value: function render() {
      this.item = $(this.svg, {
        css: {
          transform: "translateX(" + this.coordinates.x + "px) translateY(" + this.coordinates.y + "px)"
        }
      });
      $("body").append(this.item);
      return {
        width: this.item.width(),
        height: this.item.height()
      };
    }
  }]);

  return Particle;
}();

var rhombus = '<svg viewBox="0 0 12 12"> <path class="x" d="M10.3,4.3H7.7V1.7C7.7,0.8,7,0,6,0S4.3,0.8,4.3,1.7v2.5H1.7C0.8,4.3,0,5,0,6s0.8,1.7,1.7,1.7h2.5v2.5 C4.3,11.2,5,12,6,12s1.7-0.8,1.7-1.7V7.7h2.5C11.2,7.7,12,7,12,6S11.2,4.3,10.3,4.3z"/></svg>';

var pentahedron = '<svg viewBox="0 0 12 12"> <path class="x" d="M10.3,4.3H7.7V1.7C7.7,0.8,7,0,6,0S4.3,0.8,4.3,1.7v2.5H1.7C0.8,4.3,0,5,0,6s0.8,1.7,1.7,1.7h2.5v2.5 C4.3,11.2,5,12,6,12s1.7-0.8,1.7-1.7V7.7h2.5C11.2,7.7,12,7,12,6S11.2,4.3,10.3,4.3z"/></svg>';
var x = '<svg viewBox="0 0 12 12"> <path class="x" d="M10.3,4.3H7.7V1.7C7.7,0.8,7,0,6,0S4.3,0.8,4.3,1.7v2.5H1.7C0.8,4.3,0,5,0,6s0.8,1.7,1.7,1.7h2.5v2.5 C4.3,11.2,5,12,6,12s1.7-0.8,1.7-1.7V7.7h2.5C11.2,7.7,12,7,12,6S11.2,4.3,10.3,4.3z"/></svg>';

var circle = '<svg viewBox="0 0 12 12"> <path class="x" d="M10.3,4.3H7.7V1.7C7.7,0.8,7,0,6,0S4.3,0.8,4.3,1.7v2.5H1.7C0.8,4.3,0,5,0,6s0.8,1.7,1.7,1.7h2.5v2.5 C4.3,11.2,5,12,6,12s1.7-0.8,1.7-1.7V7.7h2.5C11.2,7.7,12,7,12,6S11.2,4.3,10.3,4.3z"/></svg>';

var point = '<svg viewBox="0 0 12 12"> <path class="x" d="M10.3,4.3H7.7V1.7C7.7,0.8,7,0,6,0S4.3,0.8,4.3,1.7v2.5H1.7C0.8,4.3,0,5,0,6s0.8,1.7,1.7,1.7h2.5v2.5 C4.3,11.2,5,12,6,12s1.7-0.8,1.7-1.7V7.7h2.5C11.2,7.7,12,7,12,6S11.2,4.3,10.3,4.3z"/></svg>';

function randomInt(min, max) {
  return Math.floor(Math.random() * (max - min + 1) + min);
}

var data = [point, rhombus, pentahedron, circle, x];

var isPaused = false;
window.onblur = function () {
  isPaused = true;
}.bind(this);
window.onfocus = function () {
  isPaused = false;
}.bind(this);

var particles = [];

setInterval(function () {
  if (!isPaused) {
    particles.push(new Particle(data[randomInt(0, data.length - 1)], {
      "x": Math.random() * $(window).width(),
      "y": $(window).height()
    }, 1 + Math.random() * 3));
  }
}, 200);

function update() {
  particles = particles.filter(function (p) {
    return p.move();
  });
  requestAnimationFrame(update.bind(this));
}
update();