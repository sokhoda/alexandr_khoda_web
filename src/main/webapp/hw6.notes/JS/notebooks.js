/**
 Code born in the sufferings of creative work.
 */

function clearElemContent(id){
    var myNode = document.getElementById(id);
    if (myNode == null) return;
    myNode.value='';
    myNode.innerHTML = '';
}

function clearAllAddNotebook(){
    clearElemContent('1');
    clearElemContent('2');
    clearElemContent('3');
    clearElemContent('4');
    clearElemContent('5');
    clearElemContent('message');
}


