<?php
/**
 * Plugin Name: Cell Phone Validator
 * Plugin URI: http://www.bazadeh.com/sas-cell-validator
 * Description: This plugin add a text input box to the woocommerce checkout page and send a sms code verification.
 * Version: 1.0
 * Author: Sasan Bazade
 * Author URI: http://www.bazadeh.com
 */



// create custom plugin settings menu   plugins_url("/assets/images/sas.png")   
add_action( 'admin_menu', 'sas_create_menu' ); 

function sas_create_menu(){    
	$page_title = 'WordPress Cell Phone Validator for Woocommerce';   
	$menu_title = 'PhoneValidator';   
	$capability = 'manage_options';   
	$menu_slug  = 'cell-phone-validator';   
	$function   = 'sas_settings_page';   
	$icon_url   = 'dashicons-editor-code';   
	$position   = 81;    add_menu_page( $page_title,                  $menu_title,                   $capability,                   $menu_slug,                   $function,                   $icon_url,                   $position ); 
    add_action( 'admin_init', 'update_sas_cell_phone_validator' );
    add_submenu_page(  $parent_slug = 'cell-phone-validator', 
				   $page_title = 'WordPress Cell Phone Validator for Woocommerce', 
				   $menu_title = 'General', 
				   $capability = 'manage_options', 
				   $menu_slug = 'cell-phone-validator', 
				   $function = 'sas_setting_page', 
				   $position = 1 );
	add_submenu_page(  $parent_slug = 'cell-phone-validator', 
				   $page_title = 'About SAS Cell Phone Validator', 
				   $menu_title = 'About', 
				   $capability = 'manage_options', 
				   $menu_slug = 'sas-about-us', 
				   $function = 'sas_about_page', 
                   $position = 2 );
    add_submenu_page(  $parent_slug = 'cell-phone-validator', 
				   $page_title = 'Help', 
				   $menu_title = 'Help', 
				   $capability = 'manage_options', 
				   $menu_slug = 'sas-help', 
				   $function = 'sas_help_page', 
				   $position = 2 );               
}




function sas_settings_page() {
?>
<div class="wrap">
<h1>SAS Cellphone Number Validator</h1>


</div>


<?php }

function sas_about_page() {
?>
<div class="wrap">
<h1>About SAS Cellphone Number Validator</h1>


</div>


<?php }

function sas_help_page() {
?>
<div class="wrap">
<h1>Help</h1>


</div>


<?php }


if( !function_exists("update_sas_cell_phone_validator") ) { 
	function update_sas_cell_phone_validator() {   
		register_setting( 'sas-phone-settings', 'sas_phone_num' ); 
	} 
}

if( !function_exists('sas_phone_num') ) {    
	function sas_phone_num($content)   {     
		$extra_info = get_option( 'sas_phone_num' );     
		return $content . $extra_info;   
	}  
    //add_filter( 'the_content', 'sas_phone_num' );  
    //add_action( 'woocommerce_after_order_notes', 'sas_phone_num' );
    add_action( 'woocommerce_after_order_notes', 'my_custom_checkout_field' );
}
function my_custom_checkout_field( $checkout ) {

    echo '<div id="my_custom_checkout_field"><h2>' . __('My Field') . '</h2>';

    woocommerce_form_field( 'my_field_name', array(
        'type'          => 'text',
        'class'         => array('my-field-class form-row-wide'),
        'label'         => __('Fill in this field'),
        'placeholder'   => __('Enter something'),
        ), $checkout->get_value( 'my_field_name' ));

    echo '</div>';


}


?>



