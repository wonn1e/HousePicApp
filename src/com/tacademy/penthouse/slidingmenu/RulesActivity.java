package com.tacademy.penthouse.slidingmenu;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.tacademy.penthouse.MainActivity;
import com.tacademy.penthouse.R;

public class RulesActivity extends ActionBarActivity {
	TextView rules;
	ActionBar mActionBar;
	TextView gnbTitle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rules);
		mActionBar = getSupportActionBar();
		mActionBar.show();
		mActionBar.setCustomView(R.layout.gnb_title);
		mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#40639a")));
		mActionBar.setDisplayShowTitleEnabled(false);
		mActionBar.setDisplayShowCustomEnabled(true);
		mActionBar.setDisplayHomeAsUpEnabled(false);
		mActionBar.setDisplayShowHomeEnabled(false);
		gnbTitle = (TextView)findViewById(R.id.title);
		gnbTitle.setText("�̿���");
		
		rules = (TextView)findViewById(R.id.rules);
		rules.setText("�� 1 �� (����)\n\n�� ����� �Ͽ콺��(���� ��ȸ�硱)�� �����ϴ� ���ͳ� ����Ʈ �� ����� ���ø����̼�, ����� ������ �����ϴ� ���� ���� ����(���� �����񽺡��� ��)�� �̿��Կ� �־� ȸ��� �̿����� �Ǹ�, �ǹ� �� å�ӻ���, ��Ÿ �ʿ��� ������ �������� �������� �մϴ�.\n\n�� 2 �� (����)\n\n�� ������� ����ϴ� ����� ���Ǵ� ������ �����ϴ�.\n\n1. ��ȸ�硱�� �Ͽ콺�� ȸ�簡 ��ȭ �Ǵ� �뿪(���� ����ȭ ��̶� ��)�� �̿��ڿ��� �����ϱ� ���Ͽ� ��ǻ�͵� ������ż��� �̿��Ͽ� ��ȭ ���� �ŷ��� �� �ֵ��� ������ ������ �������� ���ϸ�, �ƿ﷯ ���̹�ȸ���� ��ϴ� ������� �ǹ̷ε� ����մϴ�.\n2. ���̿��ڡ��� ��ȸ�硱�� �����Ͽ� �� ����� ���� ��ȸ�硱�� �����ϴ� ���񽺸� �޴� ȸ�� �� ��ȸ���� ���մϴ�.\n3. ��ȸ�����̶� ���� ��ȸ�硱�� ���������� �����Ͽ� ȸ������� �� �ڷμ�, ��ȸ�硱�� ������ ���������� ����������, ��ȸ�硱�� �����ϴ� ���񽺸� ��������� �̿��� �� �ִ� �ڸ� ���մϴ�. \n4. ����ȸ�����̶� ���� ȸ���� �������� �ʰ� ��ȸ�硱�� �����ϴ� ���񽺸� �̿��ϴ� �ڸ� ���մϴ�.\n5. " + "��" + "���̵�(ID)" + "��" + "�� ���� " + "��" + "ȸ��" + "��" + "�� �ĺ��� " + "��" + "����" + "��" + " �̿��� ���Ͽ� " + "��" + "ȸ��" + "��" + "�� ���ϰ� " + "��" + "ȸ��" + "��" + "�� �����ϴ� �̸��� �ּҸ� �ǹ��մϴ�.\n6. " + "��" + "��й�ȣ(Passward)" + "��" + "�� ���� " + "��" + "ȸ��" + "��" + "�� �ο� ���� " + "��" + "���̵�� ��ġ�Ǵ� " + "��" + "ȸ��" + "��" + "���� Ȯ���ϰ� ��к�ȣ�� ���� " + "��" + "ȸ��" + "��" + " �ڽ��� ���� ���� �Ǵ� ������ ������ �ǹ��մϴ�.\n7. " + "��" + "���Ἥ��" + "��" + "�� ���� " + "��" + "ȸ��" + "��" + "�� ����� �����ϴ� ���� ������ ������(���� ����������, VOD, ������ ��Ÿ ���� �������� ����) �� ���� ���񽺸� �ǹ��մϴ�.\n8. " + "��" + "�Խù�" + "��" + "�̶� ���� " + "��" + "ȸ��" + "��" + "�� " + "��" + "����" + "��" + "�� �̿��Կ� �־� " + "��" + "���񽺻�" + "��" + "�� �Խ��� ��ȣ�����ڤ������������ȭ��������� ���� ���� ������ ��, ����, ������ �� ���� ���ϰ� ��ũ ���� �ǹ��մϴ�.\n\n�� 3 �� (����� �Խÿ� ����)\n1. " + "��" + "ȸ��" + "��" + "�� �� ����� ������ " + "��" + "ȸ��" + "��" + "�� ���� �� �� �ֵ��� ȸ�� ���԰����� �Խ��մϴ�.\n2. " + "��" + "ȸ��" + "��" + "�� " + "��" + "����� ������ ���� ����" + "��" + ", " + "��" + "������Ÿ� �̿� ���� �� ������ȣ � ���� ���� (���� " + "��" + "������Ÿ���" + "��" + ")" + "��" + " �� ���ù��� �������� �ʴ� �������� �� ����� ������ �� �ֽ��ϴ�.\n3. " + "��" + "ȸ��" + "��" + "�� ����� ������ ��쿡�� �������� �� ���������� �����Ͽ� �������� �Բ� �� 3�� 1���� ��Ŀ� ���� �� ��������� �������� 30�� ������ �������� ���ϱ��� �����մϴ�. �ٸ�, ȸ������ �Ҹ��� ����� ������ ��쿡�� ���� �ܿ� �����Ⱓ ���� �� ���ڿ���, ��������, ���α��� �� ����â�� ���� ������ ������ ���� ���� ��Ȯ�� �����ϵ��� �մϴ�.\n4. " + "��" + "ȸ��" + "��" + "�� ���׿� ���� ��������� ���� �Ǵ� �����ϸ鼭 ȸ������ 30�� �Ⱓ ���� �ǻ�ǥ�ø� ���� ������ �ǻ�ǥ�ð� ǥ���� ������ ���ٴ� ���� ��Ȯ�ϰ� ���� �Ǵ� �����Ͽ������� ȸ���� ���������� �ź��� �ǻ�ǥ�ø� ���� �ƴ��� ��� ȸ���� ��������� ������ ������ ���ϴ�.\n5. " + "��" + "ȸ��" + "��" + "�� ��������� ���뿡 �������� �ʴ� ��� ȸ��� ���� ����� ������ ������ �� ������, �� ��� ȸ���� �̿����� ������ �� �ֽ��ϴ�. �ٸ�, ���� ����� ������ �� ���� Ư���� ������ �ִ� ��쿡�� ȸ��� �̿����� ������ �� �ֽ��ϴ�.\n\n�� 4 �� (����� �ؼ�)\n\n1. " + "��" + "ȸ��" + "��" + "�� " + "��" + "���Ἥ��" + "��" + " �� ���� ���񽺿� ���ؼ��� ������ �̿��� �� ��å(���� " + "��" + "���Ἥ�񽺾��" + "��" + " ��)�� �� �� ������, �ش� ������ �� ����� ������ ��쿡�� " + "��" + "���Ἥ�񽺾��" + "��" + " ���� �켱�Ͽ� ����˴ϴ�.\n2. �� ������� ������ �ƴ��� �����̳� �ؼ��� ���ؼ��� " + "��" + "���Ἥ�� ���" + "��" + " �� �� ������� �Ǵ� ����ʿ� �����ϴ�.\n\n�� 5 �� (�̿��� ü��)\n\n1. " + "��" + "�̿���" + "��" + "�� " + "��" + "ȸ��" + "��" + "�� �ǰ��� �ϴ� ��(���� " + "��" + "���Խ�û��" + "��" + ")�� ����� ���뿡 ���Ͽ� ���Ǹ� �� ���� ȸ������ ��û�� �ϰ� " + "��" + "ȸ��" + "��" + "�� �̷��� ��û�� ���Ͽ� �³������ν� ü��˴ϴ�.\n2. " + "��" + "ȸ��" + "��" + "�� " + "��" + "���Խ�û��" + "��" + "�� ��û�� ���Ͽ� " + "��" + "����" + "��" + " �̿��� �³����� ��Ģ���� �մϴ�. �ٸ�, " + "��" + "ȸ��" + "��" + "�� ���� �� ȣ�� �ش��ϴ� ��û�� ���Ͽ��� �³��� ���� �ʰų� ���Ŀ� �̿����� ������ �� �ֽ��ϴ�.\n1) �����Խ�û�ڡ��� �� ����� ���Ͽ� ������ ȸ���ڰ��� ����� ���� �ִ� ��� (��, " + "��" + "ȸ��" + "��" + "�� ȸ�� �簡�� �³��� ���� ��쿡�� ���ܷ� ��)\n2) ������ ������ �����ϰų�, " + "��" + "ȸ��" + "��" + "�� �����ϴ� ������ �������� ���� ���\n3) �̿����� ��å������ ���Ͽ� ������ �Ұ����ϰų� ��Ÿ ������ ���� ������ �����ϸ� ��û�ϴ� ���\n3. �� 5�� 1�׿� ���� ��û�� �־� " + "��" + "ȸ��" + "��" + "�� " + "��" + "ȸ��" + "��" + "�� ������ ���� ��������� ���� �Ǹ�Ȯ�� �� ���������� ��û�� �� �ֽ��ϴ�.\n4. " + "��" + "ȸ��" + "��" + "�� ���񽺰��ü����� ������ ���ų�, ����� �Ǵ� ������ ������ �ִ� ��쿡�� �³��� ������ �� �ֽ��ϴ�.\n5. �� 5�� 2�װ� 4�׿� ����, ȸ�����Խ�û�� �³��� ���� �ƴ��ϰų� ������ ���, " + "��" + "ȸ��" + "��" + "�� ��Ģ������ �̸� ���Խ�û�ڿ��� �˸����� �մϴ�.\n6. �̿����� �����ñ�� " + "��" + "ȸ��" + "��" + "�� ���ԿϷḦ ��û���� �󿡼� ǥ���� �������� �մϴ�.\n7. " + "��" + "ȸ��" + "��" + "�� " + "��" + "ȸ��" + "��" + "�� ���� ȸ����å�� ���� ��޺��� �����Ͽ� �̿�ð�, �̿�Ƚ��, ���� �޴� ���� �����Ͽ� �̿뿡 ������ �� �� �ֽ��ϴ�.\n8. " + "��" + "ȸ��" + "��" + "�� " + "��" + "ȸ��" + "��" + "�� ���Ͽ� " + "��" + "��ȭ �� �������� ���￡ ���� ����" + "��" + " �� " + "��" + "û�ҳ� ��ȣ��" + "��" + "� ���� ��� �� ���� �ؼ��� ���� �̿������̳� ��޺� ������ �� �� �ֽ��ϴ�.\n\n�� 6 �� (" + "��" + "����" + "��" + "�� ����)\n\n1. " + "��" + "ȸ��" + "��" + "�� ����� ������ �ִ� ��쿡 ���, ������� �ʿ信 ���� �����ϰ� �ִ� ���� �Ǵ� �Ϻ� " + "��" + "����" + "��" + "�� ������ �� �ֽ��ϴ�.\n2. " + "��" + "����" + "��" + "�� ����, �̿���, �̿�ð��� ���Ͽ� ������ �ִ� ��쿡�� �������, ����� ������ ���� �� �������� ���� �� ���� ���� �ش� ���� �ʱ�ȭ�鿡 �Խ��Ͽ��� �մϴ�.\n3. " + "��" + "ȸ��" + "��" + "�� ����� �����Ǵ� ������ �Ϻ� �Ǵ� ���θ� ȸ���� ��å �� ��� �ʿ�� ����, �ߴ� �� ������ �� ������, �̿� ���Ͽ� ���ù��� Ư���� ������ ���� �� " + "��" + "ȸ��" + "��" + "���� ������ ������ ���� �ʽ��ϴ�.\n\n�� 7 �� (����������ȣ �ǹ�)\n\n" + "��" + "ȸ��" + "��" + "�� " + "��" + "������Ÿ���" + "��" + " �� ���� ������ ���ϴ� �ٿ� ���� " + "��" + "ȸ��" + "��" + "�� ���������� ��ȣ�ϱ� ���� ����մϴ�. ���������� ��ȣ �� ��뿡 ���ؼ��� ���ù� �� " + "��" + "ȸ��" + "��" + "�� ����������޹�ħ�� ����˴ϴ�. �ٸ�, " + "��" + "ȸ��" + "��" + "�� ���� ����Ʈ �̿��� ��ũ�� ����Ʈ������ " + "��" + "ȸ��" + "��" + "�� ����������޹�ħ�� ������� �ʽ��ϴ�.\n\n�� 8 �� (" + "��" + "ȸ��" + "��" + "�� " + "��" + "���̵�" + "��" + " �� " + "��" + "��й�ȣ" + "��" + "�� ������ ���� �ǹ�)\n\n1. " + "��" + "ȸ��" + "��" + "�� " + "��" + "���̵�" + "��" + "�� " + "��" + "��й�ȣ" + "��" + "�� ���� ����å���� " + "��" + "ȸ��" + "��" + "���� ������, �̸� �� 3�ڰ� �̿��ϵ��� �Ͽ����� �� �˴ϴ�.\n2. " + "��" + "ȸ��" + "��" + "�� " + "��" + "ȸ��" + "��" + "�� " + "��" + "���̵�" + "��" + "�� �������� ���� ����� �ְų�, �ݻ�ȸ�� �Ǵ� ��ǳ��ӿ� ��߳��ų� " + "��" + "ȸ��" + "��" + " �� " + "��" + "ȸ��" + "��" + "�� ��ڷ� ������ ����� �ִ� ���, �ش� " + "��" + "���̵�" + "��" + "�� �̿��� ������ �� �ֽ��ϴ�.\n3. " + "��" + "ȸ��" + "��" + "�� " + "��" + "���̵�" + "��" + " �� " + "��" + "��й�ȣ" + "��" + "�� ����ǰų� �� 3�ڰ� ����ϰ� ������ ������ ��쿡�� �̸� ��� " + "��" + "ȸ��" + "��" + "�� �����ϰ� " + "��" + "ȸ��" + "��" + "�� �ȳ��� ����� �մϴ�.\n4. �� 8�� 3���� ��쿡 �ش� " + "��" + "ȸ��" + "��" + "�� " + "��" + "ȸ��" + "��" + "�� �� ����� �������� �ʰų�, ������ ��쿡�� " + "��" + "ȸ��" + "��" + "�� �ȳ��� ������ �ʾ� �߻��� �����Ϳ� ���Ͽ� " + "��" + "ȸ��" + "��" + "�� å������ �ʽ��ϴ�.\n\n\n�� 9 �� (����� ���� �� ���� ��)\n\n1. " + "��" + "ȸ��" + "��" + "�� �������� ���� �ʱ�ȭ���� �������� �Ǵ� �� ���� ���� �޴� ���� ���Ͽ� �̿��� ���� ��û�� �� �� ������, " + "��" + "ȸ��" + "��" + "�� ���ù� ���� ���ϴ� �ٿ� ���� �̸� ��� ó���Ͽ��� �մϴ�.\n2. " + "��" + "ȸ��" + "��" + "�� ����� ������ ���, ���ù� �� ����������޹�ħ�� ���� " + "��" + "ȸ��" + "��" + "�� ȸ�������� �����ϴ� ��츦 �����ϰ��� ���� ��� " + "��" + "ȸ��" + "��" + "�� ��� �����ʹ� �Ҹ�˴ϴ�.\n3. " + "��" + "ȸ��" + "��" + "�� ����� �����ϴ� ���, " + "��" + "ȸ��" + "��" + "�� �ۼ��� " + "��" + "�Խù�" + "��" + " �� ����, ���α� ��� ���� ���� ������ ��ϵ� �Խù� ��ü�� �����˴ϴ�. �ٸ�, Ÿ�ο� ���� ���, ��ũ�� ���� �Ǿ� �� �Խõǰų�, ����Խ��ǿ� ��ϵ� " + "��" + "�Խù�" + "��" + " ���� �������� ������ ������ ���� �� Ż���Ͻñ� �ٶ��ϴ�.\n\n\n�� 10 �� (" + "��" + "ȸ��" + "��" + "�� ���� ����)\n\n1. " + "��" + "ȸ��" + "��" + "�� " + "��" + "ȸ��" + "��" + "�� ���� ������ �ϴ� ��� �� ����� ���� ������ ���� �� ���� �� ���ڿ����ּ�, ��������, ����� �˸� ������ �� �� �ֽ��ϴ�.\n2. " + "��" + "ȸ��" + "��" + "�� " + "��" + "ȸ��" + "��" + " ��ü�� ���� ������ ��� 7�� �̻� " + "��" + "ȸ��" + "��" + "�� �Խ��ǿ� �Խ������ν� �� 10�� 1���� ������ ������ �� �ֽ��ϴ�.\n\n�� 11 �� (" + "��" + "ȸ��" + "��" + "�� �ǹ�)\n\n1. " + "��" + "ȸ��" + "��" + "�� ���ù��� �� ����� �����ϰų� ��ǳ��ӿ� ���ϴ� ������ ���� ������, ������̰� ���������� " + "��" + "����" + "��" + "�� �����ϱ� ���Ͽ� �ּ��� ���Ͽ� ����մϴ�.\n2. " + "��" + "ȸ��" + "��" + "�� " + "��" + "ȸ��" + "��" + "�� �����ϰ� " + "��" + "����" + "��" + "�� �̿��� �� �ֵ��� ��������(�ſ����� ����)��ȣ�� ���� ���Ƚý����� ���߾�� �ϸ� ������������޹�ħ(����������ȣ��å)���� �����ϰ� �ؼ��մϴ�.\n3. " + "��" + "ȸ��" + "��" + "�� �����̿�� �����Ͽ� " + "��" + "ȸ��" + "��" + "���κ��� ����� �ǰ��̳� �Ҹ��� �����ϴٰ� ������ ��쿡�� �̸� ó���Ͽ��� �մϴ�. " + "��" + "ȸ��" + "��" + "�� ������ �ǰ��̳� �Ҹ����׿� ���ؼ��� �Խ����� Ȱ���ϰų� ���ڿ��� ���� ���Ͽ� " + "��" + "ȸ��" + "��" + "���� ó������ �� ����� �����մϴ�.\n\n�� 12 �� (" + "��" + "�̿���" + "��" + "�� �ǹ�)\n\n1. " + "��" + "�̿���" + "��" + "�� ���� ������ �Ͽ����� �� �˴ϴ�.\n1) ��û �Ǵ� ���� �� ���������� ���\n2) Ÿ���� ��������\n3) " + "��" + "ȸ��" + "��" + "�� �Խ��� ������ ����\n4) " + "��" + "ȸ��" + "��" + "�� ���� ���� �̿��� ����(��ǻ�� ���α׷� ��) ���� �۽� �Ǵ� �Խ�\n5) " + "��" + "ȸ��" + "��" + "�� ��Ÿ ��3���� ���۱� �� �������ǿ� ���� ħ��\n6) " + "��" + "ȸ��" + "��" + " �� ��Ÿ ��3���� ������ �ջ��Ű�ų� ������ �����ϴ� ����\n7) �ܼ� �Ǵ� �������� �޽���, ȭ��, ����, ��Ÿ ��ǳ��ӿ� ���ϴ� ������ " + "��" + "����" + "��" + "�� ���� �Ǵ� �Խ��ϴ� ����\n8) ȸ���� ���� ���� ������ �������� " + "��" + "����" + "��" + "�� ����ϴ� ����\n9) ��Ÿ �ҹ����̰ų� �δ��� ����\n2. " + "��" + "ȸ��" + "��" + "�� �����, �� ����� ����, �̿�ȳ� �� " + "��" + "����" + "��" + "�� �����Ͽ� ������ ���ǻ���, " + "��" + "ȸ��" + "��" + "�� �����ϴ� ���� ���� �ؼ��Ͽ��� �ϸ�, ��Ÿ " + "��" + "ȸ��" + "��" + "�� ������ ���صǴ� ������ �Ͽ����� �� �˴ϴ�.\n\n�� 13 �� (������ ���� �� ������ ����)\n\n1. " + "��" + "ȸ��" + "��" + "�� " + "��" + "ȸ��" + "��" + "�� " + "��" + "����" + "��" + " �̿� �� �ʿ��ϴٰ� �����Ǵ� �پ��� ������ ���������̳� ���ڿ��� ���� ������� " + "��" + "ȸ��" + "��" + "���� ������ �� �ֽ��ϴ�. �ٸ�, " + "��" + "ȸ��" + "��" + "�� ���ù��� ���� �ŷ����� ���� �� �������� � ���� �亯 ���� �����ϰ��� �������� ���ڿ����� ���ؼ� ���� ������ �� �� �ֽ��ϴ�.\n2. �� 14�� 1���� ������ ��ȭ �� ���� ���۱�⿡ ���Ͽ� �����Ϸ��� �ϴ� ��쿡�� " + "��" + "ȸ��" + "��" + "�� ���� ���Ǹ� �޾Ƽ� �����մϴ�. �ٸ�, " + "��" + "ȸ��" + "��" + "�� �ŷ����� ���� �� �������� � ���� ȸ�ſ� �־�� ���ܵ˴ϴ�.\n3. " + "��" + "ȸ��" + "��" + "�� " + "��" + "����" + "��" + "�� ��� �����Ͽ� ���� ȭ��, Ȩ������, ���ڿ��� � ������ ������ �� �ֽ��ϴ�. ������ ����� ���ڿ����� ������ " + "��" + "ȸ��" + "��" + "�� ���Ű����� " + "��" + "ȸ��" + "��" + "���� �� �� �ֽ��ϴ�.\n4. " + "��" + "�̿���(ȸ��, ��ȸ�� ����)" + "��" + "�� ȸ�簡 �����ϴ� ���񽺿� �����Ͽ� �Խù� �Ǵ� ��Ÿ ������ ����, ����, �����ϴ� ���� ��ġ�� ������ �ʽ��ϴ�.\n\n�� 14 �� (���۱��� �ͼ� �� �̿�����)\n\n1. ��ȸ�硱�� �ۼ��� ���۹��� ���� ���۱� ��Ÿ ���������� ��ȸ�硱�� �ͼӵ˴ϴ�.\n2. �̿��ڴ� ��ȸ�硱�� �̿������ν� ���� ���� �� ��ȸ�硱���� ���������� �ͼӵ� ������ ��ȸ�硱�� ���� �³� ���� ����, �۽�, ����, ����, ��� ��Ÿ ����� ���Ͽ� ������������ �̿��ϰų� ��3�ڿ��� �̿��ϰ� �Ͽ����� �� �˴ϴ�.\n3. " + "��" + "ȸ�硰�� ������ ���� �̿��ڿ��� �ͼӵ� ���۱��� ��ȸ�硰 �̿ܿ� ����ϴ� ��� ���� �̿��ڿ��� �뺸�Ͽ��� �մϴ�.\n4. ��ȸ�硱�� ȸ���� ���� ���� �Խ��� �Խù��� Ÿ���� ���۱�, ���α׷����۱� ���� ħ���Ͽ����� ������ ��ȸ�硱�� Ÿ�����κ��� ���ع��û�� �� ���� ���⸦ ���� ��� ȸ���� ��ȸ�硱�� ��å�� ���Ͽ� ����Ͽ��� �ϸ�, ��ȸ�硱�� ��å���� ���� ��� ȸ���� �׷� ���� ��ȸ�硱�� �߻��� ��� ���ظ� �δ��Ͽ��� �մϴ�.\n5. ��ȸ�硱�� ȸ���� ���� ���� �Խ��� �Խù�(ȸ���� ���� ����)�� ���� �� ȣ�� ��쿡 �ش��Ѵٰ� �ǴܵǴ� ��� �������� ���� ����, ������ �� ������, �̿� ���� ��ȸ�硱�� ��� å�ӵ� ���� �ʽ��ϴ�.\n1) ����(spam)�� �Խù� �� ����� �Խù�(�� : ����� ����, Ư������Ʈ ���� ��)\n2) Ÿ���� ����� �������� ���� ����� �����Ͽ� Ÿ���� ������ �Ѽ��ϴ� ��\n3) ���� ���� Ÿ���� �Ż����, ��3���� ���۱� �� �Ǹ��� ħ���ϴ� ����, ��Ÿ �Խ��� ������ �ٸ� ������ �Խù�\n4) ��Ÿ ���� ���� �� ��ȸ�硱�� ��ħ � ���ݵȴٰ� �ǴܵǴ� ���\n6. ��ȸ�硱�� ȸ���� �Խù� ��Ͽ� ���� ���� �ɻ糪 ��������� �Խù��� ������ Ȯ�� �Ǵ� �������� ������ �� ����� ���ؼ� å���� ���� �ʽ��ϴ�.\n7. ��ȸ�硱�� ȸ�� ���� �߻��ϴ� ���� �Ǵ� �ŷ� ������ ���� �߻��ϴ� �սǰ� ���ؿ� ���� å������ �ʽ��ϴ�.\n\n�� 15 �� (����Ʈ�� ����)\n\n1. Ÿ ����Ʈ�� �����۸�ũ(�����۸�ũ�� ��󿡴� ����, �׸� �� ��ȭ�� ���� ���Ե�)��� � ���� �����ų �� �ֽ��ϴ�.\n2. ��ȸ�硱�� �̿��ڰ� �ش� �������Ʈ�� ���������� ��ǰ �Ǵ� �뿪�� �ŷ��� ������ ���ؼ��� �ƹ��� å���� �δ����� �ʽ��ϴ�.\n\n�� 16 �� (å������)\n\n1. " + "��" + "ȸ��" + "��" + "�� õ������ �Ǵ� �̿� ���ϴ� �Ұ��׷����� ���Ͽ� " + "��" + "����" + "��" + "�� ������ �� ���� ��쿡�� " + "��" + "����" + "��" + " ������ ���� å���� �����˴ϴ�.\n2. " + "��" + "ȸ��" + "��" + "�� ���ڻ�ŷ� ����� �Һ��ں�ȣ�� ���� ������ ���� ����Ǹ��߰����ڷμ� " + "��" + "����" + "��" + "�� ���� ��ǰ�� �ŷ�(�ֹ�-����-���-��ǰ ��)�� �����Ͽ� " + "��" + "�Ǹž�ü" + "��" + "�� ������ ���� �̿��ڿ��� �߻��� ��� ���ؿ� ���Ͽ��� å���� ���� �ʽ��ϴ�.\n3. " + "��" + "ȸ��" + "��" + "�� " + "��" + "ȸ��" + "��" + "�� ��å������ ���� " + "��" + "����" + "��" + " �̿��� ��ֿ� ���Ͽ��� å���� ���� �ʽ��ϴ�.\n4 " + "��" + "ȸ��" + "��" + "�� " + "��" + "ȸ��" + "��" + "�� " + "��" + "����" + "��" + "�� �����Ͽ� ������ ����, �ڷ�, ����� �ŷڵ�, ��Ȯ�� ���� ���뿡 ���Ͽ��� å���� ���� �ʽ��ϴ�.\n5. " + "��" + "ȸ��" + "��" + "�� " + "��" + "ȸ��" + "��" + " �� �Ǵ� " + "��" + "ȸ��" + "��" + "�� ��3�� ��ȣ���� " + "��" + "����" + "��" + "�� �Ű��� �Ͽ� �ŷ� ���� �� ��쿡�� å���� �����˴ϴ�.\n6. " + "��" + "ȸ��" + "��" + "�� ����� �����Ǵ� ���� �̿�� �����Ͽ� ���ù��� Ư���� ������ ���� �� å���� ���� �ʽ��ϴ�.\n\n�� 17 �� (�����ذ�)\n\n1. ��ȸ�硱�� �̿��ڷκ��� ����Ǵ� �Ҹ����� �� �ǰ��� �켱������ �� ������ ó���մϴ�. �ٸ�, �ż��� ó���� ����� ��쿡�� �̿��ڿ��� �� ������ ó�������� ��� �뺸�մϴ�.\n2. ��ȸ�硱�� �̿��� ���� �߻��� ���ڻ�ŷ� ����� �����Ͽ� �̿����� ���ر�����û�� �ִ� ��쿡�� �����ŷ�����ȸ �Ǵ� ��.�����簡 �Ƿ��ϴ� ������������� ������ ���� �� �ֽ��ϴ�.\n\n�� 18 �� (�ذŹ� �� ���ǰ���)\n\n1. " + "��" + "ȸ��" + "��" + "�� " + "��" + "ȸ��" + "��" + " �� ����� �Ҽ��� ���ѹα����� �ذŹ����� �մϴ�.\n2. " + "��" + "ȸ��" + "��" + "�� " + "��" + "ȸ��" + "��" + "�� �߻��� ���￡ ���� �Ҽ��� �λ�Ҽ۹� ���� ���ҹ����� �����մϴ�.\n\n�������� : 2014�� 8�� 27��\n�������� : 2014�� 8�� 27��\n");
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case android.R.id.home:
			startActivity(new Intent(RulesActivity.this, MainActivity.class));
		}
		return super.onOptionsItemSelected(item);
	}

}