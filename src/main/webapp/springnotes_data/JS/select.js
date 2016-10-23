/**
 * Created by s_okhoda on 17.02.2016.
 */
function setSelectIndex(id, inx){
    var obj = document.getElementById(id);
    obj.options[inx].selected = true;
}

function setSelectValue(id, val){
    var obj = document.getElementById(id);
    obj.value = val;
}

function setVisibility(name, dispType){
    var obj = document.getElementById(name);
    obj.style.display = dispType;
}
