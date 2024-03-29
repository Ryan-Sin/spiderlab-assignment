package com.ryan.spiderlab.common.exception;

public class ErrorMessage {
    public final static String SERVER_ERROR = "개발팀의 문의해주세요.";
    public static final String NOT_EXIST_MEMBER = "회원이 존재하지 않습니다.";
    public static final String EXIST_MEMBER = "회원이 존재합니다.";
    public static final String REQUIRED_VALUE_EMAIL = "이메일은는 필수입니다.";
    public static final String REQUIRED_VALUE_PASSWORD = "비밀번호는 필수입니다.";
    public static final String NOT_PASSWORD_SIZE ="비밀번호는 8자 이상 16자 이하로 입력해주세요.";
    public static final String NOT_EXIST_PASSWORD_UNAUTHORIZED = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 16자의 비밀번호여야 합니다.";
    public final static String PASSWORD_NOT_MATCH = "비밀번호가 틀렸습니다.";
    public static final String REQUIRED_NAME = "성함은 필수입니다.";
    public final static String NOT_REGEXP_RESIDENT_ID_NUMBER_UNAUTHORIZED = "올바른 주민등록 번호 약식을 작성해주세요.";
    public static final String NOT_EXIST_UNAUTHORIZED = "유효한 인증 자격 증명이 없습니다.";
    public static final String INCORRECT_FORMAT_PAGE = "올바른 페이지 값을 설정해주세요.";
    public static final String INCORRECT_FORMAT_LIMIT = "올바른 최대 값을 설정해주세요.";
    public static final String NOT_AVAILABLE_RENT_BOOK = "도서 대출이 불가능 합니다.";
    public static final String EXIST_UNIQUE_BOOK = "등록 된 ISBN가 존재합니다.";
    public static final String REQUIRED_VALUE_UNIQUE = "ISBN 값은 필수 입니다.";
}
