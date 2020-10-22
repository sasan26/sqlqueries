var Sas = /** @class */ (function () {
    function Sas() {
    }
    Sas.prototype.printName = function (message) {
        console.log(message);
    };
    return Sas;
}());
var chor = /** @class */ (function () {
    function chor() {
    }
    chor.prototype.dodor = function (kos) {
        if (kos > 10) {
            console.log(kos + "is master az 10");
        }
        else {
            console.log("riddi");
        }
    };
    return chor;
}());
var sasan = new Sas();
sasan.printName("Sasi");
var gogol = new chor();
gogol.dodor(6);
gogol.dodor(26);
var str = new String("This is string");
console.log("str.constructor is:" + str.constructor);
