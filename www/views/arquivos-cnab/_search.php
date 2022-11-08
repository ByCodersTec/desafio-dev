<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;

/* @var $this yii\web\View */
/* @var $model app\models\ArquivosCnabSearch */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="arquivos-cnab-search">

    <?php $form = ActiveForm::begin([
        'action' => ['index'],
        'method' => 'get',
    ]); ?>

    <?= $form->field($model, 'id') ?>

    <?= $form->field($model, 'tipo_transacao') ?>

    <?= $form->field($model, 'data') ?>

    <?= $form->field($model, 'valor') ?>

    <?= $form->field($model, 'cpf') ?>

    <?php // echo $form->field($model, 'cartao') ?>

    <?php // echo $form->field($model, 'hora') ?>

    <?php // echo $form->field($model, 'dono_loja') ?>

    <?php // echo $form->field($model, 'nome_loja') ?>

    <div class="form-group">
        <?= Html::submitButton(Yii::t('app', 'Search'), ['class' => 'btn btn-primary']) ?>
        <?= Html::resetButton(Yii::t('app', 'Reset'), ['class' => 'btn btn-outline-secondary']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
