<?php
/**
 * @package     Joomla.Site
 * @subpackage  Templates.beez3
 * @copyright   Copyright (C) 2005 - 2013 Open Source Matters, Inc. All rights reserved.
 * @license     GNU General Public License version 2 or later; see LICENSE.txt
 */

// No direct access.
defined('_JEXEC') or die;

JLoader::import('joomla.filesystem.file');


// Check modules
$showRightColumn  = ($this->countModules('position-3') or $this->countModules('position-6') or $this->countModules('position-8'));
$showbottom      = ($this->countModules('position-9') or $this->countModules('position-10') or $this->countModules('position-11'));
$showleft      = ($this->countModules('position-4') or $this->countModules('position-7') or $this->countModules('position-5'));

if ($showRightColumn == 0 and $showleft == 0)
{
  $showno = 0;
}

JHtml::_('behavior.framework', true);

// Get params
$color        = $this->params->get('templatecolor');
$logo        = $this->params->get('logo');
$navposition    = $this->params->get('navposition');
$headerImage    = $this->params->get('headerImage');
$app        = JFactory::getApplication();
$doc        = JFactory::getDocument();
$templateparams    = $app->getTemplate(true)->params;
$config = JFactory::getConfig();

$bootstrap = explode(',', $templateparams->get('bootstrap'));
$jinput = JFactory::getApplication()->input;
$option = $jinput->get('option', '', 'cmd');

if (in_array($option, $bootstrap))
{
  // Load optional rtl Bootstrap css and Bootstrap bugfixes
  JHtml::_('bootstrap.loadCss', true, $this->direction);
}

$doc->addStyleSheet(JURI::base() . 'templates/system/css/system.css');
$doc->addStyleSheet(JURI::base() . 'templates/' . $this->template . '/css/print.css', $type = 'text/css', $media = 'print');
$doc->addStyleSheet(JURI::base() . 'templates/' . $this->template . '/css/general.css', $type = 'text/css', $media = 'screen,projection');

if ($this->direction == 'rtl')
{
  $doc->addStyleSheet($this->baseurl . '/templates/' . $this->template . '/css/template_rtl.css');
  if (file_exists(JPATH_SITE . '/templates/' . $this->template . '/css/' . $color . '_rtl.css'))
  {
    $doc->addStyleSheet($this->baseurl . '/templates/' . $this->template . '/css/' . htmlspecialchars($color) . '_rtl.css');
  }
}

JHtml::_('bootstrap.framework');

?>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="<?php echo $this->language; ?>" lang="<?php echo $this->language; ?>" dir="<?php echo $this->direction; ?>" >
  <head>
    <?php require __DIR__ . '/jsstrings.php';?>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=3.0, user-scalable=yes"/>
    <meta name="HandheldFriendly" content="true" />
    <meta name="apple-mobile-web-app-capable" content="YES" />

    <jdoc:include type="head" />

    <!--[if IE 7]>
    <link href="<?php echo $this->baseurl ?>/templates/<?php echo $this->template; ?>/css/ie7only.css" rel="stylesheet" type="text/css" />
    <![endif]-->
  </head>
  <body>
    <div class="PageBackgroundGradient"></div>
    <div class="PageBackgroundGlare">
      <div class="PageBackgroundGlareImage"></div>
    </div>
    <div class="Main">
      <div class="Sheet">
        <div class="Sheet-tl"></div>
        <div class="Sheet-tr"><div></div></div>
        <div class="Sheet-bl"><div></div></div>
        <div class="Sheet-br"><div></div></div>
        <div class="Sheet-tc"><div></div></div>
        <div class="Sheet-bc"><div></div></div>
        <div class="Sheet-cl"><div></div></div>
        <div class="Sheet-cr"><div></div></div>
        <div class="Sheet-cc"></div>
        <div class="Sheet-body">
          <div style="margin-top: 9px; height: 20px; background-color: #595959"></div>
          <jdoc:include type="modules" name="user3" />
          <div class="Header">
            <div class="Header-jpeg"></div>
          </div>
          <div class="contentLayout">
            <div class="sidebar1">
              <jdoc:include type="modules" name="position-7" style="artblock" />
            </div>
            <div class="content">
              <jdoc:include type="component" />
            </div>
            
          </div>
          <div class="cleared"></div>
          <div class="Footer">
            <div class="Footer-inner">
              <div class="Footer-text">
                <p>Copyright &copy; 2009 - <?php echo date('Y');?> Alfons Rickert GmbH.</p>
                <!-- Erstellt von <a href="http://www.oeing.eu">Christian Oeing</a>.-->
                <p><b>Alfons Rickert GmbH</b>, Quantwick 37, 48683 Ahaus-Wüllen, <img src="images/icons/phone.png" style="width: 10px;"/> 02561 987 406 <img src="images/icons/fax.png" style="width: 10px;"/> 02561 987 408</p>
                <p><b>STR Transport-Logistik GmbH</b>, Industriestraße 12, 27383 Scheeßel, <img src="images/icons/phone.png" style="width: 10px;"/> 04263 985 794 1 <img src="images/icons/fax.png" style="width: 10px;"/> 04263 931 644</p>
              </div>
            </div>
            <div class="Footer-background"></div>
          </div>
          
        </div>
      </div>      
    </div>
            
    <jdoc:include type="modules" name="debug" />
    
  </body>
</html>
