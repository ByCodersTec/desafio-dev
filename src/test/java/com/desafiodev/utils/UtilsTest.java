package com.desafiodev.utils;

import com.desafiodev.application.domains.*;
import com.desafiodev.application.domains.ids.StoreId;
import com.desafiodev.application.domains.ids.TransactionId;
import com.google.common.testing.NullPointerTester;
import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public abstract class UtilsTest {
  protected <T> void assertClass(Class<T> tClass, T instance) {
    NullPointerTester test =
        new NullPointerTester()
            .setDefault(Cpf.class, Fixture.getCpf())
            .setDefault(CreditCard.class, Fixture.getCreditCard())
            .setDefault(TransactionType.class, Fixture.getTransactionType())
            .setDefault(Transaction.class, Fixture.getTransaction())
            .setDefault(Cnab.class, Fixture.getCnab())
            .setDefault(StoreId.class, Fixture.getStoreId())
            .setDefault(TransactionId.class, Fixture.getTransactionId())
            .setDefault(Store.class, Fixture.getStore());
    test.testAllPublicStaticMethods(tClass);
    test.testAllPublicInstanceMethods(instance);
    test.testAllPublicConstructors(tClass);
    EqualsVerifier.forClass(tClass).suppress(Warning.STRICT_INHERITANCE).verify();
    ToStringVerifier.forClass(tClass).verify();
  }
}
