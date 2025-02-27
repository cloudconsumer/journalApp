package com.mishra.journal;

import com.mishra.journal.entity.User;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class ArguementBuilderTest implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.of(User.builder().username("tom").password("tom").build()),
                Arguments.of(User.builder().username("ram").password("ram").build()),
                Arguments.of(User.builder().username("spam").password("spam").build())
        );
    }
}
