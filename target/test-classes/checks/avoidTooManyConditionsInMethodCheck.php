<?php
function functionUnderTest(){
i=1
if (i == 0) {
    echo "i equals 0";
    }
if (i == 1) {
    echo "i equals 1";
    i=2;
}
switch (i) {
    case 0:
        echo "i equals 0";
        break;
    case 1:
        echo "i equals 1";
        break;
if (i == 2) {
    echo "i equals 2";
    i=1;
}
?>