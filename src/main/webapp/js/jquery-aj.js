


function shownhide(idsh)
{
    if (document.getElementById(idsh).style.display == "none") {
        document.getElementById(idsh).style.display = "block";
        //document.getElementById("myDIV").style.display = "none";
    } else {
        document.getElementById(idsh).style.display = "none";
    }
}
function innhe(hdid)
{
//ajaxshow('?call=inhoadon&hdid=<%=hoadon_id%>','inhoadondiv');
    printWindow = window.open(myWindow, "?call=inhoadon&hdid=" + hdid + "");
    printWindow.print();
    printWindow.close();
}

function openWindow(hdid) {
    var win = window.open("?call=inhoadon&hdid=" + hdid + "", "", "width=400,height=600");
    setTimeout(function () {
        win.print();
        win.document.close();
    }, 1000)
}


function ajaxshow2(url, id, url2, id2)
{
    j = getXMLHttpRequestObject();
    if (j != false)
    {
        j.open("POST", url, true);

        j.onreadystatechange = function ()
        {
            if (j.readyState == 4)
            {
                if (id != "") {
                    document.getElementById(id).innerHTML = j.responseText;
                }

            }
        }
        j.send();
    } else
    {
        //alert("Cant create XMLHttpRequest");
    }

    j2 = getXMLHttpRequestObject();
    if (j2 != false)
    {
        j2.open("POST", url2, true);

        j2.onreadystatechange = function ()
        {
            if (j2.readyState == 4)
            {
                if (id2 != "") {
                    document.getElementById(id2).innerHTML = j2.responseText;
                }

            }
        }
        j2.send();
    } else
    {
        //alert("Cant create XMLHttpRequest");
    }

}

function ajaxshow(url, ids)
{
    j = getXMLHttpRequestObject();
    if (j != false)
    {
        j.open("POST", url, true);
        j.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        j.onreadystatechange = function ()
        {
            if (j.readyState == 4)
            {
                if (ids != "") {
                    document.getElementById(ids).innerHTML = j.responseText;
                    ids = null;
                }

            }
        }
        j.send();
    } else
    {
        alert("Cant create XMLHttpRequest");
    }

}



//Getting the right XMLHttpRequest object 
function getXMLHttpRequestObject()
{


    xmlhttp = 0;
    try
    {
        // Try to create object for Chrome, Firefox, Safari, IE7+, etc.      
        xmlhttp = new XMLHttpRequest();
    } catch (e)
    {
        try
        {
            // Try to create object for later versions of IE.
            //document.write("Loacding...");
            xmlhttp = new ActiveXObject('MSXML2.XMLHTTP');
        } catch (e)
        {
            try
            {
                // Try to create object for early versions of IE.
                xmlhttp = new ActiveXObject('Microsoft.XMLHTTP');
            } catch (e)
            {
                // Could not create an XMLHttpRequest object.
                return false;
            }
        }
    }
    return xmlhttp;
}


let timerOn = true;
function timer(remaining, div, divbtn) {
    var m = Math.floor(remaining / 60);
    var s = remaining % 60;

    m = m < 10 ? '0' + m : m;
    s = s < 10 ? '0' + s : s;
    var show;
    if (remaining > 59) {
        show = m + ":" + s;
    } else {
        show = s;
    }
    document.getElementById(div).innerHTML = " (" + show + ") ";
    remaining -= 1;

    if (remaining >= 0 && timerOn) {
        setTimeout(function () {
            timer(remaining, div, divbtn);
        }, 1000);
        return;
    }

    if (!timerOn) {
        // Do validate stuff here
        return;
    }

    // Do timeout stuff here
    //alert('Timeout for otp');
    document.getElementById(divbtn).disabled = false;
    document.getElementById("txtMobile").disabled = false;
    document.getElementById(div).innerHTML = "";

}

function timerLogin(remaining, div) {
    var m = Math.floor(remaining / 60);
    var s = remaining % 60;

    m = m < 10 ? '0' + m : m;
    s = s < 10 ? '0' + s : s;
    var show;
    if (remaining > 59) {
        show = m + ":" + s;
    } else {
        show = s;
    }
    document.getElementById(div).innerHTML = " (" + show + ") ";
    remaining -= 1;

    if (remaining >= 0 && timerOn) {
        setTimeout(function () {
            timerLogin(remaining, div);
        }, 1000);
        return;
    }

    if (!timerOn) {
        // Do validate stuff here
        return;
    }

    // Do timeout stuff here
    //alert('Timeout for otp');


}



function ajShowContent(url) {
    var advfile = url
    var txtFile = new XMLHttpRequest();
    txtFile.open("GET", advfile, true);
    txtFile.onreadystatechange = function () {
        if (txtFile.readyState === 4) {
            if (txtFile.status === 200) {
                allText = txtFile.responseText;
                //document.getElementById(div).innerHTML = allText.toString();
                return  allText.toString();
            }
        }
    }
    txtFile.send(null);
}





(function ($) {

    $.fn.showMore = function (options) {

        "use strict";

        var currentelem = 1;

        this.each(function () {

            var currentid = '';
            var element = $(this);
            var auto = parseInt(element.innerHeight()) / 2;
            var fullheight = element.innerHeight();
            var settings = $.extend({
                minheight: auto,
                buttontxtmore: "show more",
                buttontxtless: "show less",
                buttoncss: "showmore-button",
                animationspeed: auto
            }, options);

            element.attr('id') != undefined ? currentid = element.attr('id') : currentid = currentelem;
            element.wrap("<div id='showmore-" + currentid + "' data-showmore style='max-width:" + element.css('width') + ";'></div>");

            if (element.parent().not('[data-showmore]')) {

                if (fullheight > settings.minheight) {

                    element.css('min-height', settings.minheight).css('max-height', settings.minheight).css('overflow', 'hidden');
                    var showMoreButton = $("<div />", {
                        id: "showmore-button-" + currentid,
                        "class": settings.buttoncss,
                        click: function () {

                            if (element.css('max-height') != 'none') {
                                element.css('height', settings.minheight).css('max-height', '').animate({height: fullheight}, settings.animationspeed, function () {
                                    showMoreButton.text(settings.buttontxtless);
                                });
                            } else {
                                element.animate({height: settings.minheight}, settings.animationspeed, function () {
                                    showMoreButton.text(settings.buttontxtmore);
                                    element.css('max-height', settings.minheight);
                                });
                            }
                        },
                        text: settings.buttontxtmore
                    });

                    element.after(showMoreButton);

                }

                currentelem++;

            }

        });

        return this;

    };

}(jQuery));