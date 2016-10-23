/**
 * Created by al1 on 28.11.15.
 */

val = 55;
console.log('Hello JS' + val);
function createTree(node, tabs) {
    tabs += '&nbsp;&nbsp;&nbsp;';
    document.writeln(tabs + node.tagName + '<br/>');
    for (var i=0; i < node.childNodes.length; i++) {
        createTree(node.childNodes.item(i), tabs);
    }
}
var functionFun = function() {
    //alert(vaz);
    //alert('Fun function ' + arguments.length);
    var vaz = 4;
    //var obj = [];

    var obj = {
        field : 1,
        field2 : 'поле',
        du : function () {
            alert(this.field + this.field2 + this.id);
        }
    };
    obj['id'] = 1;
    function Class() {
        this.field = 12;
        this.method = function() {
            this.field = 45;
            return this.field;
        };
        this.toString = function() {
            return 2;
        }
    }

    var obj = new Class();
    //alert(obj.field + obj.method());

    var vector = new Array(3);

    //var sub = Object.create(obj);
    var sub = {};
    sub.__proto__ = obj;


    alert(obj + sub);


};



//alert(functionFun());
