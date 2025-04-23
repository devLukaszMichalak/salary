package dev.lukaszmichalak.salary.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.LazyInitializer;
import org.junit.jupiter.api.Test;

class UserTest {

  @Test
  void equals_shouldBeReflexive() {
    // given
    User user = createUserWithId(1L);

    // expect
    assertThat(user).isEqualTo(user);
  }

  @Test
  void equals_shouldBeReflexive_proxy() {
    // given
    User user = createUserProxy(1L);

    // expect
    assertThat(user).isEqualTo(user);
  }

  @Test
  void equals_shouldBeSymmetric() {
    // given
    User user1 = createUserWithId(1L);
    User user2 = createUserWithId(1L);

    // expect
    assertThat(user1).isEqualTo(user2);
    assertThat(user2).isEqualTo(user1);
  }

  @Test
  void equals_shouldBeSymmetric_proxy() {
    // given
    User user1 = createUserProxy(1L);
    User user2 = createUserProxy(1L);

    // expect
    assertThat(user1).isEqualTo(user2);
    assertThat(user2).isEqualTo(user1);
  }

  @Test
  void equals_shouldBeSymmetric_mixed() {
    // given
    User user1 = createUserWithId(1L);
    User user2 = createUserProxy(1L);

    // expect
    assertThat(user1).isEqualTo(user2);
    assertThat(user2).isEqualTo(user1);
  }

  @Test
  void equals_shouldBeTransitive() {
    // given
    User user1 = createUserWithId(1L);
    User user2 = createUserWithId(1L);
    User user3 = createUserWithId(1L);

    // expect
    assertThat(user1).isEqualTo(user2);
    assertThat(user2).isEqualTo(user3);
    assertThat(user1).isEqualTo(user3);
  }

  @Test
  void equals_shouldBeTransitive_proxy() {
    // given
    User user1 = createUserProxy(1L);
    User user2 = createUserProxy(1L);
    User user3 = createUserProxy(1L);

    // expect
    assertThat(user1).isEqualTo(user2);
    assertThat(user2).isEqualTo(user3);
    assertThat(user1).isEqualTo(user3);
  }

  @Test
  void equals_shouldBeTransitive_mixed() {
    // given
    User user1 = createUserWithId(1L);
    User user2 = createUserProxy(1L);
    User user3 = createUserWithId(1L);

    // expect
    assertThat(user1).isEqualTo(user2);
    assertThat(user2).isEqualTo(user3);
    assertThat(user1).isEqualTo(user3);
  }

  @Test
  void equals_shouldReturnFalseForDifferentIds() {
    // given
    User user1 = createUserWithId(1L);
    User user2 = createUserWithId(2L);

    // expect
    assertThat(user1).isNotEqualTo(user2);
  }

  @Test
  void equals_shouldReturnFalseForDifferentIds_proxy() {
    // given
    User user1 = createUserProxy(1L);
    User user2 = createUserProxy(2L);

    // expect
    assertThat(user1).isNotEqualTo(user2);
  }

  @Test
  void equals_shouldReturnFalseForDifferentIds_mixed() {
    // given
    User user1 = createUserWithId(1L);
    User user2 = createUserProxy(2L);

    // expect
    assertThat(user1).isNotEqualTo(user2);
  }

  @Test
  void equals_shouldReturnFalseForNull() {
    // given
    User user = createUserWithId(1L);

    // expect
    assertThat(user).isNotEqualTo(null);
  }

  @Test
  void equals_shouldReturnFalseForNull_proxy() {
    // given
    User user = createUserProxy(1L);

    // expect
    assertThat(user).isNotEqualTo(null);
  }

  @Test
  void equals_shouldReturnFalseForDifferentClass() {
    // given
    User user = createUserWithId(1L);
    Object otherObject = "not a user";

    // expect
    assertThat(user).isNotEqualTo(otherObject);
  }

  @Test
  void equals_shouldReturnFalseForDifferentClass_proxy() {
    // given
    User user = createUserProxy(1L);
    Object otherObject = "not a user";

    // expect
    assertThat(user).isNotEqualTo(otherObject);
  }

  @Test
  void hashCode_shouldBeConsistentWithEquals() {
    // given
    User user1 = createUserWithId(1L);
    User user2 = createUserWithId(1L);
    User user3 = createUserWithId(2L);

    // expect
    assertThat(user1.hashCode()).isEqualTo(user2.hashCode());
    assertThat(user1.hashCode()).isNotEqualTo(user3.hashCode());
  }

  @Test
  void hashCode_shouldBeConsistentWithEquals_proxy() {
    // given
    User user1 = createUserProxy(1L);
    User user2 = createUserProxy(1L);
    User user3 = createUserProxy(2L);

    // expect
    assertThat(user1.hashCode()).isEqualTo(user2.hashCode());
    assertThat(user1.hashCode()).isNotEqualTo(user3.hashCode());
  }

  @Test
  void hashCode_shouldBeConsistentWithEquals_mixed() {
    // given
    User user1 = createUserProxy(1L);
    User user2 = createUserProxy(1L);
    User user3 = createUserWithId(2L);

    // expect
    assertThat(user1.hashCode()).isEqualTo(user2.hashCode());
    assertThat(user1.hashCode()).isNotEqualTo(user3.hashCode());
  }

  private User createUserWithId(Long id) {
    User user = new User("test@example.com", "password");
    setPrivateId(user, id);
    return user;
  }

  private User createUserProxy(Long id) {
    User user = new ProxyUser("test@example.com", "password");
    setPrivateId(user, id);
    return user;
  }

  private void setPrivateId(User user, Long id) {
    try {
      var field = User.class.getDeclaredField("id");
      field.setAccessible(true);
      field.set(user, id);
      field.setAccessible(false);
    } catch (Exception e) {
      throw new RuntimeException("Failed to set ID field", e);
    }
  }

  private static class ProxyUser extends User implements HibernateProxy {

    ProxyUser(String email, String password) {
      super(email, password);
    }

    private final LazyInitializer initializer = mock(LazyInitializer.class);

    {
      doReturn(User.class).when(initializer).getPersistentClass();
    }

    @Override
    public Object writeReplace() {
      throw new UnsupportedOperationException();
    }

    @Override
    public LazyInitializer getHibernateLazyInitializer() {
      return initializer;
    }
  }
}
