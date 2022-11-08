<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;

/* @var $this yii\web\View */
/* @var $model app\models\ArquivosCnab */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="arquivos-cnab-form">

    <?php $form = ActiveForm::begin(); ?>

    <?= $form->field($model, 'tipo_transacao')->textInput() ?>

    <?= $form->field($model, 'data')->textInput() ?>

    <?= $form->field($model, 'valor')->textInput() ?>

    <?= $form->field($model, 'cpf')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'cartao')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'hora')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'dono_loja')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'nome_loja')->textInput(['maxlength' => true]) ?>

    <div class="form-group">
        <?= Html::submitButton(Yii::t('app', 'Save'), ['class' => 'btn btn-success']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
