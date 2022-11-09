<?php

namespace app\modules\api\controllers;

use yii\rest\ActiveController;

/**
 * Default controller for the `api` module
 */
class DefaultController extends ActiveController
{
   public $modelClass = 'app\models\ArquivosCnab';
}
