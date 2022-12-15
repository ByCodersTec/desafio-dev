<?php

namespace app\models;

use yii\base\Model;
use yii\data\ActiveDataProvider;
use app\models\ArquivosCnab;

/**
 * ArquivosCnabSearch represents the model behind the search form of `app\models\ArquivosCnab`.
 */
class ArquivosCnabSearch extends ArquivosCnab
{
    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['id', 'tipo_transacao'], 'integer'],
            [['data', 'cpf', 'cartao', 'hora', 'dono_loja', 'nome_loja'], 'safe'],
            [['valor'], 'number'],
        ];
    }

    /**
     * {@inheritdoc}
     */
    public function scenarios()
    {
        // bypass scenarios() implementation in the parent class
        return Model::scenarios();
    }

    /**
     * Creates data provider instance with search query applied
     *
     * @param array $params
     *
     * @return ActiveDataProvider
     */
    public function search($params)
    {
        $query = ArquivosCnab::find();

        // add conditions that should always apply here

        $dataProvider = new ActiveDataProvider([
            'query' => $query,
        ]);

        $this->load($params);

        if (!$this->validate()) {
            // uncomment the following line if you do not want to return any records when validation fails
            // $query->where('0=1');
            return $dataProvider;
        }

        // grid filtering conditions
        $query->andFilterWhere([
            'id' => $this->id,
            'tipo_transacao' => $this->tipo_transacao,
            'data' => $this->data,
            'valor' => $this->valor,
        ]);

        $query->andFilterWhere(['like', 'cpf', $this->cpf])
            ->andFilterWhere(['like', 'cartao', $this->cartao])
            ->andFilterWhere(['like', 'hora', $this->hora])
            ->andFilterWhere(['like', 'dono_loja', $this->dono_loja])
            ->andFilterWhere(['like', 'nome_loja', $this->nome_loja]);

        return $dataProvider;
    }
}
