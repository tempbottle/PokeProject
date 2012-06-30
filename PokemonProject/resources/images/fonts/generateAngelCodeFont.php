<?php
/*
<script>
function ord (string) {
var str = string + '',
code = str.charCodeAt(0);
if (0xD800 <= code && code <= 0xDBFF) { // High surrogate (could change last hex to 0xDB7F to treat high private surrogates as single characters)
var hi = code;
if (str.length === 1) {
return code; // This is just a high surrogate with no following low surrogate, so we return its value;
// we could also throw an error as it is not a complete character, but someone may want to know
}
var low = str.charCodeAt(1);
return ((hi - 0xD800) * 0x400) + (low - 0xDC00) + 0x10000;
}
if (0xDC00 <= code && code <= 0xDFFF) { // Low surrogate
return code; // This is just a low surrogate with no preceding high surrogate, so we return its value;
// we could also throw an error as it is not a complete character, but someone may want to know
}
return code;
}
var str="âäçèéêëìíîïñòóôöùúûüàáå";
for(i=0;i<str.length;i++)
document.write(str.charCodeAt(i)+",");
</script>*/

Header("Content-Type: text/plain; charset=utf-8");

$str[]='ABCDEFGHIJKLMNOPQRSTUVWXYZ';
$str[]='abcdefghijklmnopqrstuvwxyz';
$str[]='!"#$%&\'()*+,-./0123456789:';
$str[]=';<=>?@[\]^_`{|}~…•¤       ';
$str[]=array(194,196,199,200,201,202,203,204,205,206,207,209,210,211,212,214,217,218,219,220,192,193,197);//$str[]='ÂÄÇÈÉÊËÌÍÎÏÑÒÓÔÖÙÚÛÜÀÁÅ';
$str[]=array(226,228,231,232,233,234,235,236,237,238,239,241,242,243,244,246,249,250,251,252,224,225,229);//$str[]='âäçèéêëìíîïñòóôöùúûüàáå';
$str[]=array(9792,9794);
$width=11;
$height=13;
$xadvance=7;

$_generatedStr='';
$_charCount=0;
for($i=0;$i<count($str);$i++){
	if(is_array($str[$i]))
		$ordList=$str[$i];
	else
		for($j=0;$j<strlen($str[$i]);$j++){
			$ordList[$j]=ord($str[$i][$j]);}
			
	for($j=0;$j<count($ordList);$j++){
		$_generatedStr.="char id=".$ordList[$j]."   x=".($width*$j)."    y=".($height*$i)."    width=".$width."    height=".$height."   xoffset=0     yoffset=0     xadvance=".$xadvance."     page=0  chnl=15\n";}
	$_charCount+=$j;
}

echo'info face="FONT_NAME" size=-'.$height.' bold=0 italic=0 charset="" unicode=1 stretchH=100 smooth=0 aa=1 padding=0,0,0,0 spacing=1,1 outline=0
common lineHeight='.($height+4).' base=13 scaleW=256 scaleH=256 pages=1 packed=0 alphaChnl=1 redChnl=0 greenChnl=0 blueChnl=0
page id=0 file="FONT SPRITE SHEET FILE.png";
chars count='.$_charCount."\n".$_generatedStr;

/*for($i=123;$i<=126;$i++)
echo "char id=".$i."   x=".(72+6*($i-123))."    y=36    width=6    height=12   xoffset=0     yoffset=0     xadvance=7     page=0  chnl=15\n";?>*/