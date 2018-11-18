package com.ardublock.translator.block.plaquino;

/* おまじない */
import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;
import com.ardublock.translator.block.TranslatorBlock;

public class PQLED extends TranslatorBlock
{

	/* コンストラクタ: 引数と基底クラスコンストラクタの呼び出しはテンプレパターン*/
	public PQLED(Long blockId, Translator translator,
			String codePrefix, String codeSuffix, String label) {
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		//↓ラベルの名前を取得する　(使わない)
		//String subroutineName = label.trim();

		//ソケットにつながったブロックを取得
		TranslatorBlock translatorBlock = getTranslatorBlockAtSocket(0);

		String pin_led = translatorBlock.toCode();

		//ソケットにつながったブロックを取得
		translatorBlock = getTranslatorBlockAtSocket(1);

		String onoff = translatorBlock.toCode();

		return "digitalWrite(" + pin_led + " ," + onoff + ");\n";
	}
}
