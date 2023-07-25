-- テーブル作成
CREATE TABLE TESTUSER01.ACCESSHISTORY (
    connection_id NUMBER PRIMARY KEY,  -- 接続ID（仮定）
    user_id NUMBER,                    -- ユーザーID（仮定）
    login_time TIMESTAMP,              -- ログイン時間（仮定）
    logout_time TIMESTAMP,             -- ログアウト時間（仮定）
    source_ip VARCHAR2(50),            -- 接続元IPアドレス（仮定）
    status VARCHAR2(20)                -- 接続ステータス（仮定）
);

-- テーブルコメント
COMMENT ON TABLE TESTUSER01.NEWTABLE IS '接続履歴を保存するテーブル';

-- カラムコメント（任意）
COMMENT ON COLUMN TESTUSER01.NEWTABLE.connection_id IS '接続ID';
COMMENT ON COLUMN TESTUSER01.NEWTABLE.user_id IS 'ユーザーID';
COMMENT ON COLUMN TESTUSER01.NEWTABLE.login_time IS 'ログイン時間';
COMMENT ON COLUMN TESTUSER01.NEWTABLE.logout_time IS 'ログアウト時間';
COMMENT ON COLUMN TESTUSER01.NEWTABLE.source_ip IS '接続元IPアドレス';
COMMENT ON COLUMN TESTUSER01.NEWTABLE.status IS '接続ステータス';