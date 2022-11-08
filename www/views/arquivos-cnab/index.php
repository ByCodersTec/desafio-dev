<?php

use yii\helpers\Html;
use yii\grid\GridView;

/* @var $this yii\web\View */
/* @var $searchModel app\models\ArquivosCnabSearch */
/* @var $dataProvider yii\data\ActiveDataProvider */

$this->title = Yii::t('app', 'Arquivos Cnabs');
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="arquivos-cnab-index">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <?= Html::a(Yii::t('app', 'Create Arquivos Cnab'), ['create'], ['class' => 'btn btn-success']) ?>
    </p>

    <?php // echo $this->render('_search', ['model' => $searchModel]); ?>

    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'filterModel' => $searchModel,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            'id',
            'tipo_transacao',
            'data',
            'valor',
            'cpf',
            //'cartao',
            //'hora',
            //'dono_loja',
            //'nome_loja',

            ['class' => 'yii\grid\ActionColumn'],
        ],
    ]); ?>


</div>
