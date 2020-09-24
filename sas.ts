

class Sas{
    printName(message):void{
        
        console.log(message)
    }
}

class chor {
    dodor(kos):void{
        if(kos>10){
            console.log(kos + "is master az 10");
        } else {
            console.log("riddi");
        }
    }
}
var sasan = new Sas();
sasan.printName("Sasi");

var gogol = new chor();
gogol.dodor(6);
gogol.dodor(26);


var str = new String( "This is string" ); 
console.log("str.constructor is:" + str.constructor);

