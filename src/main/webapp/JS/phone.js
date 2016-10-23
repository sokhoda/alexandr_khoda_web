/**
 Code born in the sufferings of creative work.
 */
var tbl, arr;

function Phone(no, balance) {
    this.no = no;
    this.balance = balance;
    this.tariff = 1;
    this.call = function (toNo, minutes) {
        if (minutes * this.tariff > this.balance) {
            try {
                throw new Error('Not enough fund to make a call! ' + this.balance);
            }
            catch (err) {
                alert(new Date() + ': ' + err);
            }
        }
        else {
            this.balance -= minutes * this.tariff;
            alert(new Date() + ': абонент номер = ' + this.no + ' звонит' +
                ' абоненту номер = ' + toNo + ".\n Новый баланс " + this.balance);
        }
    };
    this.getNo = function () {
        return this.no;
    };
    this.setNo = function (no) {
        this.no = no;
    };
    this.getBalance = function () {
        return this.balance;
    };
    this.setBalance = function (balance) {
        this.balance = balance;
    };
    this.toString = function () {
        return this.getNo() + ', ' + this.getBalance();
    };

}

function generatePhoneTable(tableRows) {
    arr = new Array();
    var i;
    for (i = 0; i < tableRows; i++) {
        arr.push(new Phone(getRandNumber(), Math.round(Math.random() * 1000)));
    }
    removeAllChildElem(document.getElementsByTagName('body')[0]);
    refreshPage(true);
}

function refreshPage(doCreate) {
    if (doCreate) {
        var body = document.getElementsByTagName('body')[0];
        tbl = document.createElement('table');

        var reloadButton = getNewButton('reload', 'reload', 'reload', 'but redrawBut');
        var p = document.createElement('label');
        p.setAttribute('id', 'message');
        p.setAttribute('class', 'message');

        body.appendChild(reloadButton);
        body.appendChild(p);
        body.appendChild(tbl);
    }
    redrawTable();
    setAttr('css/table.css');
}

function setAttr(cssFile) {
    var head = document.getElementsByTagName('head')[0];
    var link = document.createElement('link');
    link.rel = 'stylesheet';
    link.type = 'text/css';
    link.href = cssFile;
    head.appendChild(link);
}

function removeAllChildElem(node) {
    if (node == null) return;
    while (node.firstChild) {
        node.removeChild(node.firstChild);
    }
}

function removeElemByID(id, selfRemoval) {
    var myNode = document.getElementById(id);
    if (myNode == null) return;
    while (myNode.firstChild) {
        myNode.removeChild(myNode.firstChild);
    }
    if (selfRemoval) {
        myNode.parentNode.removeChild(myNode);
    }
}

function redrawTable() {
    var row, cell, button, butEdit;
    removeAllChildElem(tbl);
    setMessage('');
    var cnt = 0;
    for (var i = 0; i < arr.length; i++) {
        if (arr[i] == null) continue;
        row = tbl.insertRow(cnt++);
        row.setAttribute("id", i + "row");

        cell = row.insertCell(0);
        cell.setAttribute("class", "column-left-bottom");
        cell.innerHTML = arr[i].getNo();

        cell = row.insertCell(1);
        cell.setAttribute("class", "column-left-bottom right");
        cell.innerHTML = arr[i].getBalance();

        cell = row.insertCell(2);
        cell.setAttribute("class", "but");
        cell.appendChild(getNewButton("delete", 'delButton', i + 'delButton', "but"));

        cell = row.insertCell(3);
        cell.setAttribute("class", "but");
        cell.appendChild(getNewButton("edit", 'edButton', i + 'edButton', "but"));
    }

    row = tbl.createTHead().insertRow(0);

    cell = row.insertCell(0);
    cell.setAttribute("class", "column-left-bottom top");
    cell.innerHTML = "<b> Phone Numbers </b>";

    cell = row.insertCell(1);
    cell.setAttribute("class", "column-left-bottom top right");
    cell.innerHTML = "<b> Balance </b>";
}

function addTextEdit(rowColNo, name, focus) {
    var edit, value;
    value = getPhoneData(rowColNo);
    edit = getNewTextEdit(value, 'edit', rowColNo[0] + name, "edit" + name);

    var cellVal = tbl.rows.namedItem(rowColNo[0] + "row").cells[rowColNo[1]];
    cellVal.innerHTML = '';
    cellVal.appendChild(edit);
    if (focus) {
        edit.focus();
    }
}

function delAllEditByName(name) {
    var editList = document.getElementsByName(name);

    while (editList.length) {
        var R1C1 = getRowColNo(editList[0]);
        editList[0].parentNode.innerHTML = getPhoneData(R1C1);
    }
}

function butEditOnClick(butEdit) {
    var rowColNo = getRowColNo(butEdit);
    if (rowColNo == null) return;
    delAllEditByName('edit');
    addTextEdit([rowColNo[0], 0], 'edNo', true);
    addTextEdit([rowColNo[0], 1], 'edBal', false);
}

function butDeleteOnClick(butDel) {
    var rowColNo = getRowColNo(butDel);
    if (rowColNo == null) return;

    delAllEditByName('edit');
    delete arr[rowColNo[0]];
    removeElemByID(rowColNo[0] + "row", true);
}

function editOnBlur(edit) {
    var rowColNo = getRowColNo(edit);
    if (rowColNo == null) return;
    //var cellVal = tbl.rows.namedItem(rowColNo[0] + "row").cells[rowColNo[1]];
    edit.parentNode.innerHTML = getPhoneData(rowColNo);
}

function getPhoneData(rowColNo) {
    if (rowColNo == null) return;
    if (rowColNo[1] == 0) {
        return arr[rowColNo[0]].getNo();
    }
    else if (rowColNo[1] == 1) {
        return arr[rowColNo[0]].getBalance();
    }
}

function editOnchange(edit) {
    var rowColNo = getRowColNo(edit);
    var edButton = document.getElementById(rowColNo[0] + 'edButton');
    try {
        if (rowColNo[1] == 1) {
            if ((edit.value).match(/[^0-9.,\s]/g) != null) {
                throw 'Not allowed symbols found in "' + edit.value + '". Use' +
                ' "[0-9] . ," only.';
            }
            arr[rowColNo[0]].setBalance(edit.value);
        }
        else if (rowColNo[1] == 0) {
            if ((edit.value).match(/[^0-9 +\s]/g) != null) {
                throw 'Not allowed symbols found in "' + edit.value + '". Use' +
                ' "[0-9]" only.';
            }
            arr[rowColNo[0]].setNo(edit.value);
        }
        setMessage('');
    }
    catch (err) {
        setMessage(err, edButton);
    }
}

function setMessage(text, refElement) {
    var label = document.getElementById('message');
    label.innerHTML = text;
    if (refElement != null) {
        var obj = getPosition(refElement);
        label.style.left = (obj.x + refElement.offsetWidth + 20) + 'px';
        label.style.top = (obj.y) + 'px';
    }

}

function getRowColNo(node) {
    var nodeID = node.getAttribute("id");
    var rowNo = parseInt(nodeID);
    if (isNaN(rowNo)) {
        return null;
    }
    var colNo = 0;
    if (nodeID.indexOf('edBal') > -1) {
        colNo = 1;
    }

    return [rowNo, colNo];
}

function getNewTextEdit(value, name, id, clas) {
    var edit = document.createElement('input');
    edit.setAttribute("type", "text");
    edit.setAttribute("name", name);
    edit.setAttribute("value", value);
    edit.setAttribute("id", id);
    edit.setAttribute("class", clas);
    edit.onchange = function () {
        editOnchange(edit);
    };
    edit.onblur = function () {
        editOnBlur(edit)
    };

    return edit;
}

function getNewButton(text, name, id, clas) {
    var button = document.createElement('button');
    button.setAttribute("type", "submit");
    button.setAttribute("name", name);
    button.setAttribute("id", id);
    button.setAttribute("class", clas);
    if (name == 'delButton') {
        button.onclick = function () {
            butDeleteOnClick(button);
        };
    }
    else if (name == 'edButton') {
        button.onclick = function () {
            butEditOnClick(button);
        };
    }
    else if (name == 'reload') {
        button.onclick = function () {
            redrawTable();
        };
    }
    button.innerText = text;
    return button;
}

function getRandNumber() {
    var codes = ['095', '050', '067', '099', '097'];
    var inx = Math.floor(codes.length * Math.random());
    var str1 = codes[inx];
    for (i = 0; i < 7; i++) {
        if (i == 0 || i == 3 || i == 5) {
            str1 += ' ';
        }
        str1 += Math.floor(Math.random() * 10);
    }
    return str1;
}

function getPosition(element) {
    var xPosition = 0;
    var yPosition = 0;

    while (element) {
        xPosition += (element.offsetLeft - element.scrollLeft + element.clientLeft);
        yPosition += (element.offsetTop - element.scrollTop + element.clientTop);
        element = element.offsetParent;
    }
    var scr = getScrollXY();
    return {x: xPosition + scr[0], y: yPosition + scr[1]};
}


function getScrollXY() {
    var x = 0, y = 0;
    if (typeof( window.pageYOffset ) == 'number') {
        // Netscape
        x = window.pageXOffset;
        y = window.pageYOffset;
    } else if (document.body && ( document.body.scrollLeft || document.body.scrollTop )) {
        // DOM
        x = document.body.scrollLeft;
        y = document.body.scrollTop;
    } else if (document.documentElement && ( document.documentElement.scrollLeft || document.documentElement.scrollTop )) {
        // IE6 standards compliant mode
        x = document.documentElement.scrollLeft;
        y = document.documentElement.scrollTop;
    }
    return [x, y];
}

//var phone1 = new Phone('067 070 07 07', 20);
//alert(phone1.getNo());
//var phone2 = new Phone('095 090 09 09', 25);
//try {
//    phone1.call('095 090 09 09', 10);
//    phone2.call('067 070 07 07', 20);
//    phone2.call('067 070 07 07', 5.5);
//}
//catch(e) {
//    alert(e.name);
//}

