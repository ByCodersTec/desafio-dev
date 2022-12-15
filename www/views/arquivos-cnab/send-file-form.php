<?php

use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $model app\models\ArquivosCnabForm */

$this->title = Yii::t('app', 'Enviar Arquivo Cnab');
$this->params['breadcrumbs'][] = ['label' => Yii::t('app', 'Arquivos Cnabs'), 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="arquivos-cnab-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('file-form', [
        'model' => $model,
    ]) ?>

</div>
