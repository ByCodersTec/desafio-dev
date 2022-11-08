<?php

use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $model app\models\ArquivosCnab */

$this->title = Yii::t('app', 'Create Arquivos Cnab');
$this->params['breadcrumbs'][] = ['label' => Yii::t('app', 'Arquivos Cnabs'), 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="arquivos-cnab-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
