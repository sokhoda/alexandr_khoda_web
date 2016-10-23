/**
 * Created by s_okhoda on 23.01.2016.
 */

function mirrorArray(vector, uBound){
    var temp, i;
    for(i = 0; i < uBound; i++){
        temp = vector[i];
        console.log('vect[i]=' + vector[i] + ' vector[vector.length - 1 - i]=' +
            vector[vector.length - 1 - i]);
        vector[i] = vector[vector.length - 1 - i];
        vector[vector.length - 1 - i] = temp;
    }
    return vector;
}
 global = 1;

 var functionFun = function (){
    //alert('Fun function' + arguments.length);
    //alert('global =' + global);


     //var obj =     {
         //    field : 1,
         //    field2 : 'поле',
         //    funfield : function () {
         //        alert(this.field + this.field2 + this.id);
         //    }
         //};
         //obj.id = 1;
         ////obj.funfield();
         //
         //function Class(){
         //    this.field = 12;
         //    this.method = function(){
         //        this.field = 33;
         //        return this.field;
         //    };
         //}
         //var vector = new Array(3);
         //var obj = new Class();
         ////var sub = Object.create(obj);
         //
         //var sub = {};
         //sub.__proto__ = obj;
         ////sub.__proto__ = vector;
         //
         //alert(sub.hasOwnProperty('field'));

    var d;


 };
function printDOM(pref, node){
    document.writeln(pref + node.tagName + " = " + node.innerText +  '<br>' );
    pref += '&nbsp;&nbsp;&nbsp;';
    var i = 0;
    while (i < node.childNodes.length){
        printDOM(pref, node.childNodes.item(i++));
    }
}

function getTree(){
    var doc = document.documentElement;
        printDOM('', doc);

};

//alert(functionFun());

