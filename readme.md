Api仕様書



メモ
localhost:18888

```command
#window上のから接続する方法
curl http://localhost:18888/

```


詰まった点
wsl上のubuntuからwindws上で実行しているエコーサーバーに接続する方法
※WSLからWindowsのローカルホスト（127.0.0.1またはlocalhost）にアクセスするのではなく、Windowsのネットワークアダプターが割り当てられたIPアドレス（192.168.3.7と推測される）にアクセスする必要があります。


```linux
curl http://192.168.3.7:18888/
```


## build
mvn clean packageコマンドを実行すれば、targetディレクトリに実行可能なJARファイルが生成されます。

なお、<mainClass>に指定するクラスは、そのプロジェクトのメインメソッド（public static void main(String[] args)）を持つクラスをフルクラス名で指定します。

buileのエンコード>>#UTF-8

## jarファイル配置場所

```
/home/vscode/jar
```

## 実行スクリプト配置場所
```
/home/vscode/scripts/
```