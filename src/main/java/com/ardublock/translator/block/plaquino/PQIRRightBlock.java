package com.ardublock.translator.block.plaquino;

/* おまじない */
import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;
import com.ardublock.translator.block.TranslatorBlock;

public class PQIRRightBlock extends TranslatorBlock
{

	/* コンストラクタ: 引数と基底クラスコンストラクタの呼び出しはテンプレパターン*/
	public PQIRRightBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
        // setup()内に書いておく処理
		translator.addSetupCommand("pinMode(A5, INPUT);");

        return codePrefix + "analogRead(A5)" + codeSuffix;
	}
}
