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
    }

    var obj = new Class();
    //alert(obj.field + obj.method());

    var vector = new Array(3);
//vector.splice()
    
    //var sub = Object.create(obj);
    var sub = {};
    sub.__proto__ = obj;

    //alert(sub.hasOwnProperty('field'));

   function Obj() {
         this._data = "foo";
        this.getData = function() {
            return this._data;
        };
        this.setData = function(data) {
            this._data = data;
        }
    }


    var incaps = new Obj();
    alert(incaps.getData());
    incaps.setData(12);
    alert(incaps.getData());
    //alert(incaps._data);
    //obj.du();

    //var doc = document.documentElement;
    //document.write(doc.nodeType);
    //document.write(doc.tagName);
    //document.write(doc.childNodes);
};
//alert(functionFun());
